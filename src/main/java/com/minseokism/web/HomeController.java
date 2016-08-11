package com.minseokism.web;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.minseokism.domain.User;
import com.minseokism.service.UserService;

@Controller
public class HomeController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
    @RequestMapping("/")
    String index(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    	if(req.getCookies() != null && session.isNew()) {
    		Cookie[] cookies = req.getCookies();
    		User user;
        	for (int i = 0 ; i<cookies.length; i++){
        		if(cookies[i].getName().equals("asiu")){
        			log.info("[autoSignInUser !] ------------ ");
        			user = userService.autoSignIn(cookies[i]);
                	if(user != null) {
            			session.setAttribute("signInUser", user);
            			session.setMaxInactiveInterval(60*60);
            			
            			HashMap<String, String> userMap = new HashMap<String, String>();
            			userMap.put("id", user.getId());
            			userMap.put("token", user.getToken());
            			
            			cookies[i].setValue(userMap.toString());
            			cookies[i].setPath("/");
            			cookies[i].setMaxAge(200*24*60*60);
            			
            			res.addCookie(cookies[i]);
                	}
        		}
    		}
    	} 	
		return "/main";
    }
}
