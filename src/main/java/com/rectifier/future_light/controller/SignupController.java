package com.rectifier.future_light.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rectifier.future_light.dto.SignupDto;
import com.rectifier.future_light.service.SignupService;

@Controller
public class SignupController {

	@Autowired
	SignupService signupService;

	@GetMapping("/signup")
	public String signup(@ModelAttribute SignupDto signupDto) {
		return "signup";
	}

	@PostMapping("/signup/save")
	public String signupSave(SignupDto signupDto) {

		signupService.saveUser(signupDto);
		signupService.saveAdditionalInfo(signupDto);

		return "redirect:/login";
	}

}
