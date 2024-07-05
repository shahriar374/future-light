package com.rectifier.future_light.service;

import org.springframework.stereotype.Service;

@Service
public class GeneService {
    
    public String reconstructDna(String userDnaSequence, String partnerDnaSequence) {

        // Not real algorithm
        for (int i = 0; i < 3; i++) {
            userDnaSequence += partnerDnaSequence; 
            partnerDnaSequence += userDnaSequence;
        }

        return userDnaSequence + partnerDnaSequence;
    }

}
