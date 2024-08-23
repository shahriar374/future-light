package com.rectifier.future_light.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeminiChatService {

    @Autowired
    private MarkdownService markdownService;

    @Autowired
    private ChatService chatService;

    @Autowired
	UserService userService;

    private final WebClient webClient;

    @Value("${GEMINI_API_KEY}")
    private String GeminiApiKey;

    public GeminiChatService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://generativelanguage.googleapis.com").build();
    }

    public String generateResponse(String userMessage) {
        String endpoint = "/v1beta/models/gemini-1.5-flash-latest:generateContent?key=" + GeminiApiKey;

        // JSON payload
        String jsonBody = "{"
                + "\"contents\": ["
                + "    {"
                + "        \"parts\": ["
                + "            {\"text\": \"" + userMessage + "\"}"
                + "        ]"
                + "    }"
                + "]}";

        String response = webClient.post()
                .uri(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Saving USER response to DB
        chatService.saveUserMessage(userService.getCurrentUser().getUsername(), userMessage);

        String extractedText = extractTextFromResponse(response);

        return extractedText;
    }

    private String extractTextFromResponse(String jsonResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode candidatesNode = rootNode.path("candidates").get(0);
            JsonNode textNode = candidatesNode.path("content").path("parts").get(0).path("text");
            String response = markdownService.convertToHtml(textNode.asText());

            // Saving BOT response to DB
            chatService.saveBotMessage(userService.getCurrentUser().getUsername(), textNode.asText());

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error extracting response text";
        }
    }

}
