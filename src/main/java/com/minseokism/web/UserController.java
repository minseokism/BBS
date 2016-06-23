package com.minseokism.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.minseokism.domain.User;
import com.minseokism.service.UserService;

@Controller
@RequestMapping("users")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	String List(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "users/main";
	}
	
	@RequestMapping(value ="signup", method = RequestMethod.GET)
	String signUp() {
		return "users/signup";
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	String create(User user) {
		userService.create(user);
		return "redirect:/users";
	}
}
