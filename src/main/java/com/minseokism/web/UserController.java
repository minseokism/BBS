package com.minseokism.web;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.minseokism.service.EncryptionService;
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
import com.minseokism.service.UserService.state;

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
			log.info("[id exist? ]");
			return userService.checkId(id);
		} 
		
		if(!email.equals("unknown")) {
			log.info("[email exist? ]");
			return userService.checkEmail(email);
		}
		
		return false;
	}

	@RequestMapping(value ="checkInUpdate", method = RequestMethod.POST)
	@ResponseBody boolean checkInUpdate(String id,
								@RequestParam(value = "email", defaultValue ="unknown") String email,
										@RequestParam(value ="pwd", defaultValue = "unknown") String pwd) {
		log.info("[validation check in update !] ------------");

		if (!email.equals("unknown")) {
			log.info("[email exist? ]");
			return userService.checkEmail(email, id);
		}

		if (!pwd.equals("unknown")) {
			log.info("[pwd check !]");
			return userService.checkPwd(pwd, id);
		}
		return false;
	}
	
	@RequestMapping(value ="signup", method = RequestMethod.GET)
	String signUpPage() {
		log.info("[signup page !] ------------ ");
		return "users/signupForm";
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
		return "users/signinForm";
	}
	
	@RequestMapping(value = "signin", method = RequestMethod.POST)
	String signIn(User user, Model model, @RequestParam(value="autoSignIn",defaultValue = "off") String autoSignIn,
					HttpSession session, HttpServletResponse res) {
		log.info("[signin !] ------------ ");
		String tryId = user.getId(); //로그인 실패에서 비밀번호만 틀렸을경우 사용
		User signInUser = userService.signIn(user, autoSignIn);
		int stateCode = signInUser.getState();		
				
		if (state.signIn.ordinal() == stateCode) {
			log.info("[signin success !] ------------ ");
			session.setAttribute("signInUser", signInUser);
			session.setMaxInactiveInterval(60*60);
			return "redirect:/";
			
		} else if (state.autoSignIn.ordinal() == stateCode){
			log.info("[signin success : autoSignIn !] ------------ ");
			Cookie autoSignInCookie = new Cookie("autoSignInCookie", signInUser.getToken());

			autoSignInCookie.setPath("/");
			autoSignInCookie.setMaxAge(200*24*60*60);
			res.addCookie(autoSignInCookie);

			session.setAttribute("signInUser", signInUser);
			session.setMaxInactiveInterval(60*60);

			return "redirect:/";
		
		} else if (state.incorrectPwd.ordinal() == stateCode){
			log.info("[signin failure : uncorrect password !] ------------ ");
			model.addAttribute("state", stateCode);
			model.addAttribute("tryId", tryId);
			return "/users/signinForm";
		
		} else {
			log.info("[signin failure : not exist !] ------------ ");
			model.addAttribute("state", stateCode);
			return "/users/signinForm";
		}
	}
	
	@RequestMapping(value = "signout", method = RequestMethod.GET)
	String signOut(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		log.info("[signout !] ------------ ");
		User user = (User)session.getAttribute("signInUser");
		userService.deleteToken(user);
		session.invalidate();
		
		Cookie[] cookies = req.getCookies();
		for (int i = 0 ; i<cookies.length; i++){
			cookies[i].setMaxAge(0);     
			cookies[i].setPath("/");
			res.addCookie(cookies[i]);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "updateForm", method = RequestMethod.GET)
	String updateFormGate() {
		log.info("[updateFormGate page !] ------------ ");
		return "users/updateGate";
	}

	@RequestMapping(value = "updateForm", method = RequestMethod.POST)
	String updateForm(User user, Model model) {
		log.info("[updateForm signin !] ------------ ");
		User updateUser = userService.signIn(user, "off");
		int stateCode = updateUser.getState();
		
		if (state.incorrectPwd.ordinal() == stateCode) {
			model.addAttribute("error", "error");
			return "users/updateGate";
		} else{
			model.addAttribute("updateUser",updateUser);
			return "users/updateForm";
		}
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	String delete() {
		return "redirect:/";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	String update(User user, @RequestParam(value="currentPwd") String currentPwd) {
		log.info("[update submit !] ------------ ");

		if(user.getPwd().isEmpty() || user.getPwd().equals("")) {
			user.setPwd(currentPwd);
		}

		userService.update(user, currentPwd);
		return "redirect:/";
	}
}
