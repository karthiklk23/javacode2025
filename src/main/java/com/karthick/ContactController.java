package com.karthick;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
	
	
	 @Autowired
	 private JavaMailSender mailSender;
	
	@GetMapping("/contact")
	public String showContctForm() {
		
		return "contact_form";
		
	}
	
	@PostMapping("/contact")
	public String submitCotact(HttpServletRequest request) {
		
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		SimpleMailMessage message = new SimpleMailMessage();
		 
		message.setFrom("karthick.lk23@gmail.com");
		message.setTo("karthick.lk23@gmail.com");
		message.setSubject("This is a plain text email");
		message.setText("Hello guys! This is a plain text email.");
		
		String mailSubject = fullname + "has sent a message";
		String mailContent = "Sender Name : " + fullname + "\n";
		mailContent += "Send E Mail: " +email +"\n";
		mailContent += "Subject: " +subject +"\n";
		mailContent += "Content: " +content +"\n";
		
		message.setSubject(mailSubject);
		message.setText(mailContent);
		
		mailSender.send(message);

		return "message";
		
	}

}
