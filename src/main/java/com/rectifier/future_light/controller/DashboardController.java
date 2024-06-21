package com.rectifier.future_light.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

	@GetMapping("/dashboard")
	public String dashboard(Model m) {
		m.addAttribute("isDashboard", true);
		return "dashboard";
	}
	
}
