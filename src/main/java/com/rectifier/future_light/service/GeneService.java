package com.rectifier.future_light.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class GeneService {

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:5000").build();

    public String reconstructDna(String sequence1, String sequence2) {
        return webClient.post()
                .uri("/reconstruction")
                .bodyValue(Map.of("sequence1", sequence1, "sequence2", sequence2))
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (String) response.get("predicted_sequence"))
                .block();
    }

    public static boolean isValidDNASequence(String dnaSequence) {
        // Check if the sequence is not null and contains only 'A', 'C', 'G', or 'T'
        return dnaSequence != null && dnaSequence.matches("[ACGT]+");
    }

}
