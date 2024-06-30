package com.ecom.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Component
public class EmailSchedualr {

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 * * * * ?")
    public void emailScheduler() throws MessagingException, IOException, TemplateException {
        String toEmail = "dailyremotejob@gmail.com";  // Use a valid email address
        String subject = "Scheduled Email";
        String text = "This is a scheduled email sent every minute.";
        String image=" D:\\EmailSend\\image.png  ";
        String pdf="D:\\EmailSend\\day.pdf";
        emailService.sendEmail(toEmail, subject, text,image,pdf);
        System.out.println("Scheduled email sent successfully");
    }

   
    // For multiple email sending the code 
    
     @Scheduled(cron = "0 * * * * ?")
    public void emailSchedularMuiltiple() throws MessagingException {
    	String []toEmail = {"anndprakash@gmail.com","dailyremotejob@gmail.com"};  // Use a valid email address
        String subject = "Scheduled Email multiple email id";
        String text = "This is a scheduled email sent every minute. in multiple mail id";
        String [] cc= {"dailyremotejob@gmail.com"};
        String []bcc={"anandprakash824@gmail.com"};
        String image=" D:\\EmailSend\\image.png  ";
        String pdf="D:\\EmailSend\\day.pdf";
        emailService.sendEmailMultiple(toEmail,subject,text,cc,bcc);
        System.out.println("Mail Send Successfully for multiple mail id");
    }
    
    
    
    
    
    
    
}
