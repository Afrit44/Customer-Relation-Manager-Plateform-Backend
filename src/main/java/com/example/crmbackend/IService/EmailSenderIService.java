package com.example.crmbackend.IService;

public interface EmailSenderIService {

    public void sendEmail(String email, String subject, String body);
}
