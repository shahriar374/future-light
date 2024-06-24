package com.rectifier.future_light.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rectifier.future_light.dto.SignupDto;
import com.rectifier.future_light.service.DashboardService;

@Controller
public class AllusersController {

    @Autowired
    DashboardService dashboardService;
    
    @PostMapping("/allusers/update")
	public String updateDashboardInfo(SignupDto dto) {
		dashboardService.updateUser(dto);

		return "redirect:/allusers?updated";
	}

	@PostMapping("/allusers/delete/{username}")
	public String deleteUser(@PathVariable("username") String username) {
		dashboardService.deleteUser(username);

		return "redirect:/allusers?deleted";
	}

}
