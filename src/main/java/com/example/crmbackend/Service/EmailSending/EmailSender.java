package com.example.crmbackend.Service.EmailSending;

import com.example.crmbackend.Model.*;
import com.example.crmbackend.Repository.CustomerRepository;
import com.example.crmbackend.Repository.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@Configuration
@ComponentScan
public class EmailSender {

    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OfficerRepository officerRepository;

    public static void main(String[] args) {
        SpringApplication.run(EmailSender.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerMail() throws MessagingException {
//        emailSenderService.sendEmail("afrit.karim@gmail.com",
//                "This is email body",
//                "This is email subject");
//
//    }

    //Send email to all officers once a new customer creates a request.
    public void sendNewRequestEmail(Officer officer, Request request){
        Customer customer = customerRepository.findCustomerByEmail(request.getCustomerEmail());
        emailSenderService.sendEmail(officer.getEmail(),
                "[New request created by customer]",
                "Dear our beloved "+officer.getFirstName()+", \n" +
                        "We hope you're fine and in good health, \n" +
                        "We would like to inform you about the new request created by "
                        +customer.getFirstName()+" "+customer.getLastName()+" his id : "+customer.getId()+
                        " .\nPlease dear officer to change the state of the request to INEXECUTION and fulfill the request.\n" +
                        "One the request is fulfilled please change the state to VALIDATED.\n" +
                        "Thanks you, you're are our source of success.\n" +
                        "Kind Regards, \n" +
                        "ArabSoft's Administration");
    }

    //Send email to the customer to update him of the request's state
    public void sendNotificationToCustomer(Customer customer,Request request){
        if (customer.getGender().equals(Gender.MALE)){
            if (request.getCurrentState().equals(State.INEXECUTION)){
                emailSenderService.sendEmail(customer.getEmail(),
                        "[Request In Execution]",
                        "Dear Mr."+customer.getFirstName()+" "+customer.getLastName()+", \n" +
                                "We hope you are fine and in good health, \n" +
                                "We would like to inform you that your request is being executed at this moment.\n " +
                                "once the request is fulfilled you will receive an email of validation.\n" +
                                "Thanks you for trusting our services.\n" +
                                "Kind Regards, \n" +
                                "ArabSoft's Administration");
            }
            if (request.getCurrentState().equals(State.VALIDATED)){
                emailSenderService.sendEmail(customer.getEmail(),
                        "[Request Validated]",
                        "Dear Mr."+customer.getFirstName()+" "+customer.getLastName()+", \n" +
                                "We hope you are fine and in good health, \n" +
                                "We would like to inform you that your request is validated successfully  \n" +
                                "We are waiting for you feedback about the quality of our services.\n"+
                                "Thanks you for trusting our services.\n" +
                                "Kind Regards, \n" +
                                "ArabSoft's Administration");
            }
        }else{
            if (request.getCurrentState().equals(State.INEXECUTION)){
                emailSenderService.sendEmail(customer.getEmail(),
                        "[Request In Execution]",
                        "Dear Miss "+customer.getFirstName()+" "+customer.getLastName()+", \n" +
                                "We hope you are fine and in good health, \n" +
                                "We would like to inform you that your request is being executed at this moment.\n " +
                                "once the request is fulfilled you will receive an email of validation.\n" +
                                "Thanks you for trusting our services.\n" +
                                "Kind Regards, \n" +
                                "ArabSoft's Administration");
            }
            if (request.getCurrentState().equals(State.VALIDATED)){
                emailSenderService.sendEmail(customer.getEmail(),
                        "[Request Validated]",
                        "Dear Miss "+customer.getFirstName()+" "+customer.getLastName()+", \n" +
                                "We hope you are fine and in good health, \n" +
                                "We would like to inform you that your request is validated successfully  \n" +
                                "We are waiting for you feedback about the quality of our services.\n"+
                                "Thanks you for trusting our services.\n" +
                                "Kind Regards, \n" +
                                "ArabSoft's Administration");
            }
        }

    }

    //Send Email once User created
    public void sendUserCreationEmail(UserG userG){
        if (userG.getGender().equals(Gender.MALE)){
            emailSenderService.sendEmail(userG.getEmail(),
                    "[Welcome to Arab Soft's Family]",
                    "Dear Mr. "+userG.getFirstName()+" "+userG.getLastName()+", \n" +
                            "We hope you are fine and in good health, \n" +
                            "We would like to inform you that your account as a "+userG.getUserRole().getRoleName()+" have been created successfully.\n" +
                            "Welcome to ArabSoft's family.\n"+
                            "Thanks you for trusting our services.\n" +
                            "Kind Regards, \n" +
                            "ArabSoft's Administration");
        }
    }

}
