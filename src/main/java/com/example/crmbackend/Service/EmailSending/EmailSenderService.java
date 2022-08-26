package com.example.crmbackend.Service.EmailSending;

import com.example.crmbackend.IService.EmailSenderIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailSenderService implements EmailSenderIService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendEmail(String toEmail, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("crm.arabsoft@gmail.com");
        message.setSubject(subject);
        message.setTo(toEmail);
        message.setText(body);

        javaMailSender.send(message);


    }
}
