package com.rectifier.future_light.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rectifier.future_light.dto.SignupDto;
import com.rectifier.future_light.service.UserService;

@Controller
public class DashboardController {

	@Autowired
	UserService userService;

	@GetMapping("/dashboard")
	public String dashboard(@ModelAttribute SignupDto dto, Model m) {
		m.addAttribute("isDashboard", true);
		m.addAttribute("user", userService.getCurrentUser());
		return "dashboard";
	}

	@PostMapping("/dashboard/update")
	public String updateDashboardInfo(SignupDto dto) {
		userService.updateUser(dto);

		return "redirect:/dashboard?updated";
	}

	@GetMapping("/statistics")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String statistics() {
		return "statistics";
	}

}
