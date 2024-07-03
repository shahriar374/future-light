package com.rectifier.future_light.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class DiseaseService {

    public Map<String, Double> predictDisease(String dnaSequence, int age) {

        // Not real algorithm
        Map<String, Double> diseases = Map.of(
                "heartAttack", Math.round((Math.random() * 100) * 100) / 100.0,
                "kidneyFailure", Math.round((Math.random() * 100) * 100) / 100.0,
                "cancer", Math.round((Math.random() * 100) * 100) / 100.0,
                "diabetes", Math.round((Math.random() * 100) * 100) / 100.0,
                "stroke", Math.round((Math.random() * 100) * 100) / 100.0,
                "arthritis", Math.round((Math.random() * 100) * 100) / 100.0,
                "alzheimers", Math.round((Math.random() * 100) * 100) / 100.0);

        return diseases;
    }

}
