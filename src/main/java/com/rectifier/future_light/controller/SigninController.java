package com.rectifier.future_light.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SigninController {

	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}

}
