package com.rectifier.future_light.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DiseaseService {

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:5000").build();

    public String predictDisease(String dnaSequence) {
        return webClient.post()
                .uri("/predict")
                .bodyValue(Map.of("dna_sequence", dnaSequence))
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (String) response.get("predicted_disease"))
                .block();
    }

    public static boolean isValidDNASequence(String dnaSequence) {
        // Check if the sequence is not null and contains only 'A', 'C', 'G', or 'T'
        return dnaSequence != null && dnaSequence.matches("[ACGT]+");
    }

}
