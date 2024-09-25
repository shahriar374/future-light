package com.rectifier.future_light.controller;

import com.rectifier.future_light.model.Post;
import com.rectifier.future_light.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rectifier.future_light.model.Users;
import com.rectifier.future_light.service.UserService;

import java.util.List;

@Controller
public class HomeController {

	@Autowired
	UserService userService;

	@Autowired
	PostService postService;
	
	@GetMapping("/")
	public String home(Model m) {
		Users user = userService.getCurrentUser();
		m.addAttribute("user", user);

		List<Post> latestPosts = postService.getLatestPosts();
		m.addAttribute("latestPosts", latestPosts);

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

}
