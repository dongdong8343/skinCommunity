package com.project.skin.event;

import lombok.Getter;

@Getter
public class EmailSendEvent {
    private final String nickname;
    private final String email;
    private final String subject;
    private final String message;
    private final Object source;

    public EmailSendEvent(String username, String email, String subject, String message, Object source) {
        this.nickname = username;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.source = source;
    }
}
