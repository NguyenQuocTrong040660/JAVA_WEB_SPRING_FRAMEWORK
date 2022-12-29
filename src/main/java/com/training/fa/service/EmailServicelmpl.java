package com.training.fa.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.training.fa.DTO.request.EmailDetails;

@Service
public class EmailServicelmpl implements EmailService {

	@Autowired 
	private JavaMailSender javaMailSender;
	 
    @Value("${spring.mail.username}") private String sender;
	
	@Override
	public Boolean sendSimpleMail(EmailDetails details) {
		 // Try block to check for exceptions
        try {
 
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
             
            helper.setSubject(details.getSubject());
            helper.setFrom(sender);
            helper.setTo(details.getRecipient());
            helper.setText(details.getMsgBody(), true);
            javaMailSender.send(message);
            return true;
            
        }
 
        // Catch block to handle the exceptions
        catch (Exception e) {
            return false;
        }
	}

}
