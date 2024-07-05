package com.rectifier.future_light.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rectifier.future_light.model.Users;
import com.rectifier.future_light.service.GeneService;
import com.rectifier.future_light.service.UserService;

@Controller
public class GeneController {

    @Autowired
    UserService userService;

    @Autowired
    GeneService geneService;

    @PostMapping("/gene-result")
    public String diseaseResult(
            @RequestParam("user-dna-sequence") String userDnaSequence,
            @RequestParam("partner-dna-sequence") String partnerDnaSequence, 
            Model m) {
        Users user = userService.getCurrentUser();

        String reconstructedDnaSequence = geneService.reconstructDna(userDnaSequence, partnerDnaSequence);

        m.addAttribute("user", user);
        m.addAttribute("userDnaSequence", userDnaSequence);
        m.addAttribute("partnerDnaSequence", partnerDnaSequence);
        m.addAttribute("reconstructedDnaSequence", reconstructedDnaSequence);

        return "generesult";
    }

}
