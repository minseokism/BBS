package com.minseokism.anything;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import com.minseokism.App;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class JBCryptTest {
	
	@Test
	public void BcryptTest() {
			
		String pwd = "a4568520";
		String passwordHashed = BCrypt.hashpw(pwd, BCrypt.gensalt());
		String passwordHashed2 = BCrypt.hashpw(pwd, BCrypt.gensalt(10));
		String passwordHashed3 = BCrypt.hashpw(pwd, BCrypt.gensalt(20));

		boolean isValidPassword = BCrypt.checkpw(pwd, passwordHashed);
		boolean isValidPassword2 = BCrypt.checkpw(pwd, passwordHashed2); 
		boolean isValidPassword3 = BCrypt.checkpw(pwd, passwordHashed3); 
		
		Assert.isTrue(isValidPassword);
		Assert.isTrue(isValidPassword2);
		Assert.isTrue(isValidPassword3);
	}
}
