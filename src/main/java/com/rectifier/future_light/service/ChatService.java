package com.rectifier.future_light.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rectifier.future_light.model.ChatMessage;
import com.rectifier.future_light.model.ChatMessage.Sender;
import com.rectifier.future_light.repository.ChatMessageRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessage saveUserMessage(String username, String message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUsername(username);
        chatMessage.setSender(Sender.USER);
        chatMessage.setMessage(message);
        chatMessage.setTimestamp(LocalDateTime.now());
        return chatMessageRepository.save(chatMessage);
    }

    public ChatMessage saveBotMessage(String username, String message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUsername(username);
        chatMessage.setSender(Sender.BOT);
        chatMessage.setMessage(message);
        chatMessage.setTimestamp(LocalDateTime.now());
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getChatHistory(String username) {
        return chatMessageRepository.findByUsernameOrderByTimestamp(username);
    }

}
