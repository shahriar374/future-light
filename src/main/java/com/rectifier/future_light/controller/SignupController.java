package com.rectifier.future_light.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rectifier.future_light.dto.SignupDto;

@Controller
public class SignupController {
	
	
	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

	@GetMapping("/signup")
	public String signup(@ModelAttribute SignupDto signupDto) {
		return "signup";
	}
	
	@PostMapping("/signup/save")
	public String signupSave(SignupDto signupDto) {
		
		UserDetails user = User
				.withUsername(signupDto.getUsername())
				.password(passwordEncoder.encode(signupDto.getPassword()))
				.roles("USER")
				.build();
		
		jdbcUserDetailsManager.createUser(user);
		
		return "redirect:/login";
	}
	
}
