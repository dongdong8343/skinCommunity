package com.project.skin.global.event;

import com.project.skin.user.entities.User;
import lombok.Getter;

@Getter
public class EmailSendEvent {
    private final String nickname;
    private final String email;
    private final String subject;
    private final String message;
    private final EventType eventType;

    private EmailSendEvent(String username, String email, String subject, String message, EventType eventType) {
        this.nickname = username;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.eventType = eventType;
    }

    public static EmailSendEvent singUp(User user) {
        return new EmailSendEvent(
                user.getNickname(),
                user.getEmail(),
                "스킨로그 회원가입을 축하드립니다!",
                user.getNickname() + "님 회원가입을 축하드립니다.",
                EventType.SIGN_UP
        );
    }
}
