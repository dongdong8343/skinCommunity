package com.project.skin.service.dto;

import lombok.Getter;

@Getter
public class EmailMessage {
    private String to;
    private String subject;
    private String message;

    private EmailMessage(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    public static EmailMessage createEmailMessage(String to, String subject, String message) {
        return new EmailMessage(to, subject, message);
    }
}
