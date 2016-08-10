package com.minseokism.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.minseokism.domain.User;
import com.minseokism.service.UserService;

@Controller
@RequestMapping("users")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	String list(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "/main";
	}
	
	@RequestMapping(value ="check", method = RequestMethod.GET)
	@ResponseBody boolean check(@RequestParam(value = "id", defaultValue = "unknown") String id,
								@RequestParam(value = "email", defaultValue ="unknown") String email) {
		log.info("[validation check !] ------------");
		
		if(!id.equals("unknown")) {
			log.info("[id exist? ]= "+userService.checkId(id));
			return userService.checkId(id);
		} 
		
		if(!email.equals("unknown")) {
			log.info("[email exist? ]= "+userService.checkEmail(email));
			return userService.checkEmail(email);
		}
		
		return false;
	}
	
	@RequestMapping(value ="signup", method = RequestMethod.GET)
	String signUpPage() {
		log.info("[signup page !] ------------ ");
		return "users/signup";
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	String signUp(User user) {
		log.info("[signup submit !] ------------ ");		
		userService.create(user);
		return "users/signup_success";
	}
	
	@RequestMapping(value ="signin", method = RequestMethod.GET)
	String signInPage() {
		log.info("[signin page !] ------------ ");
		return "users/signin";
	}
	
	@RequestMapping(value = "signin", method = RequestMethod.POST)
	String signIn(User user, Model model, @RequestParam(value="autoSignIn",defaultValue = "off") String autoSignIn,
					HttpSession session, HttpServletResponse res) {
		log.info("[signin !] ------------ ");
		String tryId = user.getId(); //로그인 실패에서 비밀번호만 틀렸을경우 사용
		User signInUser = userService.signIn(user, autoSignIn);
		int state = signInUser.getState();		
				
		if (state == 2) {
			log.info("[signin success !] ------------ ");
			session.setAttribute("signInUser", signInUser);
			session.setMaxInactiveInterval(60*60);
			return "redirect:/";
			
		} else if (state == 3){
			log.info("[signin success : autoSignIn !] ------------ ");
			HashMap<String, String> userMap = new HashMap<String, String>();
			userMap.put("id", signInUser.getId());
			userMap.put("token", signInUser.getToken());
			
			Cookie autoSignInCookie = new Cookie("asiu", userMap.toString());  				
			autoSignInCookie.setPath("/");
			autoSignInCookie.setMaxAge(200*24*60*60);
			res.addCookie(autoSignInCookie);		
			return "redirect:/"; 
		
		} else if (state == 1){
			log.info("[signin failure : uncorrect password !] ------------ ");
			model.addAttribute("state", state);
			model.addAttribute("tryId", tryId);
			return "/users/signin";
		
		} else {
			log.info("[signin failure : not exist !] ------------ ");
			model.addAttribute("state", state);
			return "/users/signin";
		}
	}
	
	@RequestMapping(value = "signout", method = RequestMethod.GET)
	String signOut(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		log.info("[signout !] ------------ ");
		session.invalidate();
		
		Cookie[] cookies = req.getCookies();
		for (int i = 0 ; i<cookies.length; i++){
			cookies[i].setMaxAge(0);     
			cookies[i].setPath("/");
			res.addCookie(cookies[i]);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	String updateForm() {
		return "users/update";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	String update() {
		return "redirect:/main";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	String delete() {
		return "redirect:/main";
	}
	
}
