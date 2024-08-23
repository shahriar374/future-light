package com.rectifier.future_light.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rectifier.future_light.service.ChatService;
import com.rectifier.future_light.service.GeminiChatService;
import com.rectifier.future_light.service.UserService;

@Controller
public class ChatController {

	@Autowired
	UserService userService;

	@Autowired
	ChatService chatService;

	@Autowired
	GeminiChatService geminiChatService;

	@GetMapping("/chat")
	public String chatGet(Model m) {

		m.addAttribute("chatHistory", chatService.getChatHistory(userService.getCurrentUser().getUsername()));

		return "chat";
	}

	@PostMapping("/chat")
	@ResponseBody
	public ResponseEntity<Map<String, String>> chatPost(@RequestBody Map<String, String> request) {
		String userMessage = request.get("message");
		String botResponse = generateResponse(userMessage);

		Map<String, String> response = new HashMap<>();
		response.put("message", botResponse);

		return ResponseEntity.ok(response);
	}

	private String generateResponse(String userMessage) {

		String response = geminiChatService.generateResponse(userMessage);

		return response;
	}

}
