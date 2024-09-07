package com.rectifier.future_light.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rectifier.future_light.model.ChatMessage;
import com.rectifier.future_light.repository.ChatMessageRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessage saveUserMessage(String username, String message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUsername(username);
        chatMessage.setSender("user");
        chatMessage.setMessage(message);
        chatMessage.setTimestamp(LocalDateTime.now());
        return chatMessageRepository.save(chatMessage);
    }

    public ChatMessage saveBotMessage(String username, String message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUsername(username);
        chatMessage.setSender("model");
        chatMessage.setMessage(message);
        chatMessage.setTimestamp(LocalDateTime.now());
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getChatHistory(String username) {
        return chatMessageRepository.findByUsernameOrderByTimestamp(username);
    }

    @Transactional
    public void deleteChatHistory(String username) {
        chatMessageRepository.deleteByUsername(username);
    }

}
