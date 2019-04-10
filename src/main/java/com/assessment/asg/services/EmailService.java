package com.assessment.asg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendHTMLMessage(String to, String subject, String text);
}

@Service
class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailServiceImpl(final JavaMailSender emailSender, final TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendSimpleMessage(final String to, final String subject, final String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendHTMLMessage(final String to, final String subject, final String text) {
        try {
            Context context = new Context();
            context.setVariable("link", text);

            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "charset=ISO-8859-1");
            if (subject.equals("Registration Confirmation")) {
                mimeMessage.setContent(templateEngine.process("email/verify-email-address", context), "text/html");
            } else {
                mimeMessage.setContent(templateEngine.process("email/verify-change-password", context), "text/html");
            }
            helper.setTo(to);
            helper.setSubject(subject);
            emailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println("Messaging Exception: " + e);
        }
    }
}
