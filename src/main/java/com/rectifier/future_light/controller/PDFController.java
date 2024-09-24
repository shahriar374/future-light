package com.rectifier.future_light.controller;

import com.lowagie.text.DocumentException;
import com.rectifier.future_light.service.PDFService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class PDFController {

    @Autowired
    private PDFService pdfService;

    @GetMapping("/downloadDDPDF")
    public void downloadDDPDF(HttpServletResponse response,
                              @RequestParam String dnaSequence,
                              @RequestParam String diseasePrediction,
                              @RequestParam String email,
                              @RequestParam String username,
                              @RequestParam String fullname

    ) throws DocumentException, IOException {
        // Call the service to generate and export the PDF
        pdfService.exportDDToPDF(response, dnaSequence, diseasePrediction, email, username, fullname);
    }

    @GetMapping("/downloadGRPDF")
    public void downloadGRPDF(HttpServletResponse response,
                              @RequestParam String userDnaSequence,
                              @RequestParam String partnerDnaSequence,
                              @RequestParam String reconstructedDnaSequence,
                              @RequestParam String email,
                              @RequestParam String username,
                              @RequestParam String fullname

    ) throws DocumentException, IOException {
        // Call the service to generate and export the PDF
        pdfService.exportGRToPDF(response, userDnaSequence, partnerDnaSequence, reconstructedDnaSequence, email, username, fullname);
    }

}
