package com.minseokism;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.minseokism.domain.User;
import com.minseokism.repository.UserRepository;
	
@SpringBootApplication
public class App implements CommandLineRunner{	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User created = userRepository.save(new User(
				null,"minseokism","1234","minseok@naver.com","minseok"));
		System.out.println(created + "is created");
		
		userRepository.findAll()
			.forEach(System.out::println);
	}
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	
	}
	
}

