package com.minseokism.service;

import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.minseokism.App;
import com.minseokism.domain.Token;
import com.minseokism.domain.User;
import com.minseokism.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class UserServiceTest {
	@Autowired
	UserRepository userRepository;
	User user;
	
	@Test
	public void pwdRegexpTest() {
		HashMap<String, Boolean> testCase = new HashMap<String, Boolean>();
		testCase.put("12345678", true);
		testCase.put("abcdefghi", true);
		testCase.put("ABSDSACAEAKDSK", true);
		testCase.put("abcdefghi0@", true);
		testCase.put("@abcdefghi0", true);
		testCase.put("Xod%#%#a9921(5)", true);
		testCase.put("!@#$%^&*_()+-={}[]~`", true);
		testCase.put(",.,.,.,.?/;:'\"", true);
		testCase.put("<script>", true);
		testCase.put("", false);
		testCase.put("abcde", false);
		
		String regexp ="[A-Za-z0-9!@#$%^&*'\"<>,;:./?_\\(\\)\\{\\}\\[\\]~`+=-]{6,20}$";
		
		for(Entry<String, Boolean> entry : testCase.entrySet()){
			Assert.assertEquals(entry.getValue(), entry.getKey().matches(regexp));
		}

	}
	
	@Test
	public void signupValidateTest() {	
		user = new User();
		user.setEmail("ediya2@naver.com");
		user.setId("ediya_us2");
		user.setName("이디야");
		user.setPwd("Asdsadsad");
		
		userRepository.save(user);
		userRepository.delete(user);
	}
	
	@Test
	public void signinTest() {
		user = new User();
		user.setId("radio");
		user.setPwd("a4568520");
		
		User signInUser = userRepository.findById(user.getId());
		boolean test = BCrypt.checkpw(user.getPwd(), signInUser.getPwd());
		
		Assert.assertEquals(true, test);
	}
	
	@Test
	public void simplexJoinTest() {
		User tempUser = new User();
		tempUser.setEmail("ediya3@naver.com");
		tempUser.setId("ediya_us3");
		tempUser.setName("이디야");
		tempUser.setPwd("Asdsadsad");
		
		Token token1 = new Token();
		token1.setToken("hi");
		token1.setTokenId("bye");
		
		Token token2 = new Token();
		token2.setToken("hi2");
		token2.setTokenId("bye2");

		Token token3 = new Token();
		token3.setToken("hi3");
		token3.setTokenId("bye3");
		
		tempUser.addToken(token1);
		tempUser.addToken(token2);
		tempUser.addToken(token3);
		
		userRepository.save(tempUser);
		userRepository.delete(tempUser);
	}
	
}
