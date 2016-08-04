package com.minseokism.service;

import java.util.List;

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
	
	public User signIn(User user) {
		User signInUser = userRepository.findById(user.getId());
		
		if (signInUser != null && BCrypt.checkpw(user.getPwd(),signInUser.getPwd())) {
			return signInUser;
		} else {
			return null;
		}
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
		return BCrypt.hashpw(pwd, BCrypt.gensalt(10));
	}
	
}
