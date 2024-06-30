package com.ecom;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ecom.service.EmailSchedualr;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

@SpringBootApplication
@EnableScheduling
public class EmailSchdulerApplication {

    public static void main(String[] args) throws IOException, TemplateException {
        ConfigurableApplicationContext context = SpringApplication.run(EmailSchdulerApplication.class, args);
        EmailSchedualr job = context.getBean(EmailSchedualr.class);
        
        try {
            job.emailScheduler();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
