package com.project.skin.global.event;

import com.project.skin.global.error.exception.EmailSendFailedException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Component
@RequiredArgsConstructor
public class EmailsendEventListener {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    @TransactionalEventListener
    public void handleEmailSendEvent(EmailSendEvent event) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(event.getEmail()); // 메일 수신자
            mimeMessageHelper.setSubject(event.getSubject()); // 메일 제목
            mimeMessageHelper.setText(buildSignupMail(event.getNickname()), true); // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailSendFailedException();
        }
    }

    public String buildSignupMail(String username) {
        Context context = new Context();
        context.setVariable("username", username);
        return templateEngine.process("signup-mail", context);
    }
}
