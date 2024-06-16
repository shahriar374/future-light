package com.rectifier.future_light.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import com.rectifier.future_light.dto.SignupDto;

@Service
public class SignupService {
	
	
	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void saveUser(SignupDto signupDto) {
		UserDetails user = User
				.withUsername(signupDto.getUsername())
				.password(passwordEncoder.encode(signupDto.getPassword()))
				.roles("USER")
				.build();
		
		jdbcUserDetailsManager.createUser(user);
	}

}
