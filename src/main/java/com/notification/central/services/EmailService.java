package com.notification.central.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.notification.central.entities.User;

@Service
public class EmailService {
	
	@Autowired
	JavaMailSender javaMail;
	
	public void sendWelcomeEmail(User user) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(user.getEmail());
		message.setSubject("Welcome to Notification Central");
		message.setText(
				"Hello " + user.getName() + ",\n\n" +
	            "Your account has been successfully created!\n\n" +
	            "We're glad to have you here.\n\n" +
	            "Notification Central"
	        );
		
		javaMail.send(message);
	}
}
