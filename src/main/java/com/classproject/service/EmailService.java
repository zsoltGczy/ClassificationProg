package com.classproject.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.classproject.usersandroles.User;

@Service
public class EmailService {
	
	private final Log log = LogFactory.getLog(this.getClass());
	
	
	@Value("${spring.mail.username}")
	private String MESSAGE_FROM;
	
	@Value("${Myurl}")
	private String MY_URL;
	
	
	private JavaMailSenderImpl javaMailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	
	
	public void sendMessage(User user, String randomKey) {
		
		SimpleMailMessage message = null;
		
		try {
			
			message = new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo(user.getEmail());
			message.setSubject("Successful registration");
			message.setText("Dear " + user.getFirstName() + " " + user.getLastName() +
					"!\n \n \n Registration Successful. Thank you for registering. "
					+ "Please check your email for a confirmation. You may now Login."
					+ "\n\n\n\nYour activation link is: " + MY_URL + randomKey);
			javaMailSender.send(message);
			
		}catch(Exception e) {
			log.error("Email Error: Your email was unable to send to: " + user.getEmail() + " with the following exception: " + e);
		}
	}
	
}
