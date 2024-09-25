package com.rectifier.future_light.service;

import com.rectifier.future_light.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService() throws FileStorageException {
        this.fileStorageLocation = Paths.get("uploads")
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            throw new FileStorageException("Could not create the directory where uploaded files will be stored.");
        }
    }

    public String storeFile(MultipartFile file) {
        // Get the original file name and clean it up
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Generate a unique identifier (UUID)
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        try {
            // Check for invalid characters in the file name
            if (uniqueFileName.contains("..")) {
                throw new FileStorageException("Invalid file path sequence " + uniqueFileName);
            }

            // Define the target location with the unique file name
            Path targetLocation = this.fileStorageLocation.resolve(uniqueFileName);

            // Copy the file to the target location
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Return the unique file name (to be stored in the database)
            return uniqueFileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + uniqueFileName + ". Please try again!");
        }
    }

}

