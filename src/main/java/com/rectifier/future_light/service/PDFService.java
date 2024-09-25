package com.rectifier.future_light.service;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Service
public class PDFService {

    public void exportDDToPDF(HttpServletResponse response,
                            String dnaSequence,
                            String diseasePrediction,
                            String email,
                            String username,
                            String fullname

    ) throws DocumentException, IOException {
        // Set PDF properties
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);

        document.open();
        // Add content to PDF
        Paragraph title = new Paragraph("Disease Recognition Result", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph("Full Name: " + fullname));
        document.add(new Paragraph("Username: " + username));
        document.add(new Paragraph("Email: " + email));
        document.add(new Paragraph("Entered DNA Sequence: " + dnaSequence));
        document.add(new Paragraph("Predicted Disease: " + diseasePrediction));
        document.close();

        // Set the response to send PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Detected Disease.pdf");
        response.getOutputStream().write(out.toByteArray());
        response.getOutputStream().flush();
    }


    public void exportGRToPDF(HttpServletResponse response,
                              String userDnaSequence,
                              String partnerDnaSequence,
                              String reconstructedDnaSequence,
                              String email,
                              String username,
                              String fullname

    ) throws DocumentException, IOException {
        // Set PDF properties
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);

        document.open();
        // Add content to PDF
        Paragraph title = new Paragraph("Gene Reconstruction", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph("Full Name: " + fullname));
        document.add(new Paragraph("Username: " + username));
        document.add(new Paragraph("Email: " + email));
        document.add(new Paragraph("User DNA Sequence: " + userDnaSequence));
        document.add(new Paragraph("Partner DNA Sequence: " + partnerDnaSequence));
        document.add(new Paragraph("Reconstructed DNA Sequence: " + reconstructedDnaSequence));
        document.close();

        // Set the response to send PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Gene Reconstruction.pdf");
        response.getOutputStream().write(out.toByteArray());
        response.getOutputStream().flush();
    }

}
