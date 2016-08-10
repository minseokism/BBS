package com.minseokism.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minseokism.domain.User;
import com.minseokism.repository.UserRepository;

@Service
public class UserService{
	@Autowired
	UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findOne(Integer id) {
		return userRepository.findOne(id);
	}
	
	public User create(User user) {
		if (pwdRegexp(user.getPwd())) {
			user.setPwd(pwdEncryption(user.getPwd()));
			return userRepository.save(user);
		} else {
			return null;
		}		
	}
	
	public User signIn(User user, String autoSignIn) {
		User signInUser = userRepository.findById(user.getId());
		
		//errorState 0 = id 존재하지않을 때 , 1 = id는 존재, 비밀번호 틀릴 때, 2 = 맞을 때, 3 == 자동로그인
		if (signInUser == null) {
			signInUser = new User();
			signInUser.setState(0);
		
		} else if (BCrypt.checkpw(user.getPwd(),signInUser.getPwd())) {			
			signInUser.setState(2);
			
			if(autoSignIn.equals("on")) {
				signInUser.setToken(createToken());
				signInUser.setState(3);
				userRepository.save(signInUser);		
			}
			
		} else {
			signInUser = new User();
			signInUser.setState(1);
			
		}
		
		return signInUser;
	}
	
	public User update(User user) {
		return userRepository.save(user);
	}
	
	public void delete(Integer id) {
		userRepository.delete(id);
	}
	
	public boolean checkId(String id){
		return userRepository.existsById(id);
	}
	
	public boolean checkEmail(String email){
		return userRepository.existsByEmail(email);
	}
	
	public Boolean pwdRegexp(String pwd) {
		String regexp ="[A-Za-z0-9!@#$%^&*_\\(\\)\\{\\}\\[\\]~`+=-]{6,20}$";
		return pwd.matches(regexp);
	}
	
	private String pwdEncryption(String pwd) {
		return BCrypt.hashpw(pwd, BCrypt.gensalt(10, new SecureRandom()));
	}
	
	private String createToken() {
		return BCrypt.hashpw(new SecureRandom().nextDouble()+"", BCrypt.gensalt(12, new SecureRandom()));
	}
	
	public User autoSignIn(Cookie cookie) {
		HashMap<String, String> data = convertToStringToHashMap(cookie.getValue());
		User signInUser = userRepository.findById(data.get("id"));
		
		if(signInUser.getToken().equals(data.get("token"))){
			signInUser.setToken(createToken());
			userRepository.save(signInUser);
			return signInUser;
		}
		
		return null;
	}
	
	private HashMap<String,String> convertToStringToHashMap(String text){
		HashMap<String,String> data = new HashMap<String,String>();
		Pattern p = Pattern.compile("[\\{\\}\\=\\, ]++");
		String[] split = p.split(text);
		
		for ( int i=1; i+2 <= split.length; i+=2 ){
			data.put( split[i], split[i+1] );
		}
		return data;
	}
}
