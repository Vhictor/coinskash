package com.coinskash.service;

public interface EmailService {
    void sendSimpleMail (String toEmail,String bodyOfMail, String subject);
    void sendMailWithTemplate(String toEmail, String bodyofMail);
}
