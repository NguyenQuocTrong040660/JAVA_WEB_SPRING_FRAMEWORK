package com.training.fa.app;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@ComponentScan(basePackages = "com.training.fa.*")
@EntityScan(basePackages ="com.training.fa.model") // path of the entity model
@EnableJpaRepositories("com.training.fa.repository") // path of jpa repository 
public class MockApiCommercerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockApiCommercerApplication.class, args);
		System.out.println("Hello");
		LocalDateTime dtm = LocalDateTime.now();  
		System.out.println("The date is: " + dtm.toLocalDate()); 
		
		
		String dateStr = "2022-08-03 17:06:23.8470000";

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
		try {
			String dates =formatter1.format(formatter.parse(dateStr));
			System.out.println(dates);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	   
		
		
		Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
		System.out.println(CURRENT_FOLDER);
		
		Path currentDir = Paths.get("src/main/resources/static/product"); // currentDir = "."
	   Path fullPath = currentDir.toAbsolutePath();
	   System.out.println(fullPath);
		
		
	}

}
