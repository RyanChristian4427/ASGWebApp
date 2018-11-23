package com.assessment.drones.controllers.exceptions;

public class EmailExistsException extends Throwable{

    protected String returnUrl;

    public EmailExistsException(String msg, String aUrl) {
        super(msg);
        returnUrl = aUrl;
    }

    public EmailExistsException(String aUrl) {
        this("Email address already in use", aUrl);
    }

    public EmailExistsException() {
        this("Email address already in use", "/");
    }
}
