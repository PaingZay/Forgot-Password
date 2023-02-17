package com.example.WebAppClient.Service.Interface;

import javax.mail.MessagingException;

public interface EmailSenderService {
    void sendSimpleEmail(String toEmail, String body, String subject);
    void sendEmailWithAttachment(String toEmail, String body, String subject, String attachment) throws MessagingException;
}
