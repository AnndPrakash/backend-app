package com.ecom.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration freemarkerConfig;

    public void sendEmail(String toEmail, String subject, String text, String imagePath, String pdfPath)
            throws MessagingException, IOException, TemplateException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);

        Map<String, Object> model = new HashMap<>();
        model.put("text", text);
        String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(loadTemplate("email-template.html"), model);
        helper.setText(htmlText, true);

        if (imagePath != null && !imagePath.isEmpty()) {
            File image = new File(imagePath);
            if (image.exists()) {
                helper.addAttachment(image.getName(), image);
            }
        }

        if (pdfPath != null && !pdfPath.isEmpty()) {
            File pdf = new File(pdfPath);
            if (pdf.exists()) {
                helper.addAttachment(pdf.getName(), pdf);
            }
        }

        javaMailSender.send(message);
    }

    private Template loadTemplate(String templateName) throws IOException {
        return freemarkerConfig.getTemplate(templateName);
    }

    public void sendEmailMultiple(String[] toEmail, String subject, String text, String[] cc, String[] bcc)
            throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(text);
        if (cc != null) {
            helper.setCc(cc);
        }
        if (bcc != null) {
            helper.setBcc(bcc);
        }
        javaMailSender.send(message);
    }
}
