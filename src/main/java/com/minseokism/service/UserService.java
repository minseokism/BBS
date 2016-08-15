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
	
	public enum state{notExist, uncorrectPwd, signIn, autoSignIn};
	
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

		if (signInUser == null) {
			signInUser = new User();
			signInUser.setState(state.notExist.ordinal());
		
		} else if (BCrypt.checkpw(user.getPwd(),signInUser.getPwd())) {			
			signInUser.setState(state.signIn.ordinal());
			
			if("on".equals(autoSignIn)) {
				signInUser.setToken(createToken());
				signInUser.setState(state.autoSignIn.ordinal());
				userRepository.save(signInUser);		
			}
			
		} else {
			signInUser = new User();
			signInUser.setState(state.uncorrectPwd.ordinal());			
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
	
	public boolean pwdRegexp(String pwd) {
		String regexp ="[A-Za-z0-9!@#$%^&*_\\(\\)\\{\\}\\[\\]~`+=-]{6,20}$";
		return pwd.matches(regexp);
	}
	
	private String pwdEncryption(String pwd) {
		return BCrypt.hashpw(pwd, BCrypt.gensalt(10, new SecureRandom()));
	}
	
	private String createToken() {
		return BCrypt.hashpw(new SecureRandom().nextDouble()+"", BCrypt.gensalt(12, new SecureRandom()));
	}
	
	public void deleteToken(User user) {
		user.setToken("");
		user.setState(state.signIn.ordinal());
		userRepository.save(user);
	}
	
	public User autoSignIn(Cookie cookie) {
		HashMap<String, String> data = convertToStringToHashMap(cookie.getValue());
		User signInUser = userRepository.findById(data.get("id"));
		
		if(signInUser == null) {
			return null;
		}
		
		if(data.get("token").equals(signInUser.getToken())){
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
