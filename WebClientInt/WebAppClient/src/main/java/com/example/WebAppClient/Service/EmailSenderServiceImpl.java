package com.example.WebAppClient.Service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.WebAppClient.Service.Interface.EmailSenderService;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(
      String toEmail, String body, String subject) {

        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("foodshare.sg.service@gmail.com");
        message.setTo(toEmail);
        message.setText("Your OTP for the verification: "+ body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail Send..."+message);
    }

    public void sendEmailWithAttachment(String toEmail, String body, String subject, String attachment)  throws MessagingException {

      MimeMessage mimeMessage = mailSender.createMimeMessage();

      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
      mimeMessageHelper.setFrom("foodshare.sg.service@gmail.com");
      mimeMessageHelper.setTo(toEmail);
      mimeMessageHelper.setText(body);
      mimeMessageHelper.setSubject(subject);

      FileSystemResource fileSystem
                = new FileSystemResource(new File(attachment));

        mimeMessageHelper.addAttachment(fileSystem.getFilename(),
                fileSystem);

        mailSender.send(mimeMessage);
        System.out.println("Mail Send...");
      
    }
}