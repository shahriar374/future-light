package com.rectifier.future_light.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rectifier.future_light.dto.SignupDto;
import com.rectifier.future_light.service.DashboardService;

@Controller
public class DashboardController {

	@Autowired
	DashboardService dashboardService;

	@GetMapping("/dashboard")
	public String dashboard(@ModelAttribute SignupDto dto,Model m) {
		m.addAttribute("isDashboard", true);
		m.addAttribute("user", dashboardService.getCurrentUser());
		return "dashboard";
	}

	@PostMapping("/dashboard/update")
	public String updateDashboardInfo(SignupDto dto) {
		dashboardService.updateUser(dto);

		return "redirect:/dashboard?updated";
	}

}
