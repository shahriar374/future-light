package com.rectifier.future_light.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rectifier.future_light.model.Users;
import com.rectifier.future_light.service.DiseaseService;
import com.rectifier.future_light.service.UserService;

@Controller
public class DiseaseController {

    @Autowired
	UserService userService;

    @Autowired
    DiseaseService diseaseService;

    @PostMapping("/disease-result")
    public String diseaseResult(@RequestParam("dna-sequence") String dnaSequence, Model m) {
        Users user = userService.getCurrentUser();
        int age = user.getAge();

        Map<String, Double> diseasePrediction = diseaseService.predictDisease(dnaSequence, age);

        m.addAttribute("user", user);
        m.addAttribute("dnaSequence", dnaSequence);
        m.addAttribute("diseasePrediction", diseasePrediction);

        return "diseaseresult";
    }
    
}
