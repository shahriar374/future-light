package com.rectifier.future_light.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rectifier.future_light.model.Users;
import com.rectifier.future_light.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String home(Model m) {
		Users user = userService.getCurrentUser();
		m.addAttribute("user", user);

		return "home";
	}

	@GetMapping("/disease-recognition")
	public String disease() {
		return "disease";
	}
	
	@GetMapping("/gene-reconstruction")
	public String gene() {
		return "gene";
	}

	@GetMapping("/statistics")
	public String statistics() {
		return "statistics";
	}

}
