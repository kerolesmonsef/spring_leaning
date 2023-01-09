package com.example.qgame.services;

import com.example.qgame.helpers.dto.EmailDetails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    // Method 1
    // To send a simple email
    public MailSenderStatus sendSimpleMail(EmailDetails details) {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return new MailSenderStatus(true, null);
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return new MailSenderStatus(false, e.getMessage());
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class MailSenderStatus {
        private final boolean isSuccess;
        private final String error;
    }
}
