package com.minseokism.web;

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
        log.info("[main !] ------------ ");

        if (session.getAttribute("signInUser") != null) {
            return "/main";
        }

        User autoSignInUser = null;

        if (req.getCookies() != null) {
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("autoSignInCookie")) {
                    autoSignInUser = userService.autoSignIn(cookie);
                }
            }
        }

        if (autoSignInUser != null) {
            log.info("[auto sign in user !] ------------ ");
            Cookie autoSignInCookie = new Cookie("autoSignInCookie",autoSignInUser.getToken());

            autoSignInCookie.setPath("/");
            autoSignInCookie.setMaxAge(200*24*60*60);
            res.addCookie(autoSignInCookie);

            session.setAttribute("signInUser", autoSignInUser);
            session.setMaxInactiveInterval(60*60);
        }

        return "/main";
    }
}
