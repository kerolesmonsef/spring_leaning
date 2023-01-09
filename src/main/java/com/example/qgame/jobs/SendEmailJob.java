package com.example.qgame.jobs;

import com.example.qgame.helpers.dto.EmailDetails;
import com.example.qgame.services.EmailSenderService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

// i want this service because you may store some data in db
@Service
@Scope("prototype")
public class SendEmailJob implements Runnable {

    @Autowired
    protected EmailSenderService emailSenderService;
    private final EmailDetails emailDetails;

    public SendEmailJob(EmailDetails userDetails) {
        this.emailDetails = userDetails;
    }

    @Override
    public void run(){
        EmailSenderService.MailSenderStatus status = emailSenderService.sendSimpleMail(emailDetails);

        if (status.isSuccess()) {
            // log in db or something
        }
    }
}
