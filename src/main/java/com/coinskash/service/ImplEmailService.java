package com.coinskash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ImplEmailService implements EmailService{

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMail(String toEmail, String bodyOfMail, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("adeleyevictor40@gmail.com");
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(bodyOfMail);
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendMailWithTemplate(String toEmail, String bodyofMail) {

    }
}
