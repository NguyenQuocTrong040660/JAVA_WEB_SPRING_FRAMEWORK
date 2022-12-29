package com.training.fa.uitils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtill {
	
	public String saveFile(Path CURRENT_FOLDER ,MultipartFile image) throws IOException {
		
		
	Path staticPath = Paths.get("static");
    Path imagePath = Paths.get("images");
    if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
   	 
           Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
       }
    if(!image.isEmpty()) {
       Path file = CURRENT_FOLDER.resolve(staticPath)
               .resolve(imagePath).resolve(image.getOriginalFilename());
       
       
       try (OutputStream os = Files.newOutputStream(file)) {
           os.write(image.getBytes());
       }
       return (image.getOriginalFilename()).toString();
	}
	return "null";
}
}
