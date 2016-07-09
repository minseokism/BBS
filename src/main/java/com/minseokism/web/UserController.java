package com.minseokism.web;

import java.util.List;

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
		
		if(!id.equals("unknown")) {
			log.info("id exist? = "+userService.checkId(id));
			return userService.checkId(id);
		} 
		
		if(!email.equals("unknown")) {
			log.info("email exist? = "+userService.checkEmail(email));
			return userService.checkEmail(email);
		}
		
		return false;
	}
	
	@RequestMapping(value ="signup", method = RequestMethod.GET)
	String signUp() {
		return "users/signup";
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	String create(User user) {
		userService.create(user);
		return "redirect:/main";
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
	
	@RequestMapping(value = "singin", method = RequestMethod.POST)
	String signIn() {
		return "redirect:/main";
	}
}
