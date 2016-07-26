package com.minseokism.service;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.minseokism.App;
import com.minseokism.domain.User;
import com.minseokism.repository.UserRepository;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class UserServiceTest {
	@Autowired
	UserRepository userRepository;
	User user;
	
	@Test
	public void signupValidateTest() {	
		user = new User();
		user.setEmail("ediya@naver.com");
		user.setId("ediya_us");
		user.setName("이디야");
		user.setPwd("dleldi12");
		userRepository.save(user);
	}
	
	@After
	public void delUser(){
		userRepository.delete(user);
	}
}
