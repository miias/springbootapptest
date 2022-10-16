package com.example.test2.spring_test.Services.Email;

import com.example.test2.spring_test.Models.classes.EmailDetails;

// Interface
public interface EmailServiceInterface {

    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}