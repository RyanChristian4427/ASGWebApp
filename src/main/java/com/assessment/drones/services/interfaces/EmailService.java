package com.assessment.drones.services.interfaces;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendHTMLMessage(String to, String subject, String text);

}
