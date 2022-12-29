package com.training.fa.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.training.fa.DTO.request.EmailDetails;
import com.training.fa.service.EmailService;
import com.training.fa.userprinciple.UserDetaillmpl;

@Controller
@RequestMapping("/home")
public class ClientControllerSentMail {
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/contact")
	public String contact(Model model,Authentication authentication,Principal principal) {
		
		if(principal!=null) {
			UserDetaillmpl userDetail = (UserDetaillmpl) authentication.getPrincipal();
			model.addAttribute("userDetail", userDetail);
			model.addAttribute("body", getBody("contact"));
			return "index";
		}else {
			model.addAttribute("body", getBody("contact"));
			return "index";
		}
		
	}
	
	@PostMapping("/contact")
	public String SentMailClient(RedirectAttributes redirectAttributes,Model model,@RequestParam("name")String name,
			@RequestParam("email")String email,
			@RequestParam("msgBody")String msgBody1) {
		
		try {
		LocalDateTime dtm = LocalDateTime.now();  
		EmailDetails emailDetails = new EmailDetails();
		
		emailDetails.setRecipient(email);
		String msgBody = "<p><b> Drear TOP CYCLE, </b>"+msgBody1+"</p>";
		 msgBody += "<p><b></b>"+msgBody1+"</p>";
		 msgBody += "<p><br></p>";
		 msgBody += "<p>Thank you and Warmest Regards,</p>";
		 msgBody += "<p>"+name+"</p>";
		
		emailDetails.setMsgBody(msgBody);
		emailDetails.setSubject("Message from TOP CYCLE by "+name);
		emailService.sendSimpleMail(emailDetails);
		redirectAttributes.addFlashAttribute("message", "Sent Mail sucessfull!");
		return "redirect:/home/contact";
		}catch(Exception e) {
			System.out.println(e);
			redirectAttributes.addFlashAttribute("message", "Sent Mail failed!");
			return "redirect:/home/contact";
		}
		
	}

	

	public static String getBody(String body) {
		return body + ".jsp";
	}
	

}
