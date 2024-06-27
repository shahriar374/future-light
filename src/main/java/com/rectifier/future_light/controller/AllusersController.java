package com.rectifier.future_light.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rectifier.future_light.dto.SignupDto;
import com.rectifier.future_light.model.Users;
import com.rectifier.future_light.service.UserService;

@Controller
public class AllusersController {

	@Autowired
	UserService userService;

	@GetMapping("/allusers")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String allusers(
			@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "5") int pageSize,
			@RequestParam(defaultValue = "username") String sortBy,
			Model m) {
		
		pageNo--;
		Page<Users> usersPage = userService.findAllUsersPage(pageNo, pageSize, sortBy);

		int startingPagination = Math.max(pageNo - 2, 0);
		int endingPagination = Math.min(startingPagination + 4, usersPage.getTotalPages() - 1);

		if (startingPagination + 4 > usersPage.getTotalPages() - 1)
			startingPagination = Math.max(usersPage.getTotalPages() - 5, 0);

		m.addAttribute("usersPage", usersPage);
		m.addAttribute("sortBy", sortBy);
		m.addAttribute("startingPagination", startingPagination);
		m.addAttribute("endingPagination", endingPagination);

		return "allusers";
	}

	@PostMapping("/allusers/update")
	public String updateDashboardInfo(SignupDto dto) {
		userService.updateUser(dto);

		return "redirect:/allusers?updated";
	}

	@PostMapping("/allusers/delete/{username}")
	public String deleteUser(@PathVariable("username") String username) {
		userService.deleteUser(username);

		return "redirect:/allusers?deleted";
	}

}
