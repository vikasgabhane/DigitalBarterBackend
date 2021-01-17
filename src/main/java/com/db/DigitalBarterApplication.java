package com.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.db.dto.User;

@SpringBootApplication
public class DigitalBarterApplication {

	@Autowired
    private JavaMailSender javaMailSender;
	
	public static void main(String[] args) {
		SpringApplication.run(DigitalBarterApplication.class, args);
	}

	public void sendEmail(User user) {
	
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmailId());

        msg.setSubject("Digital Barter");
        msg.setText("Your Digital Barter Password is :- "+user.getPassword());

        javaMailSender.send(msg);
		
	}
}
