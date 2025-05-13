package com.project.skin.user.dto;

import com.project.skin.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AddUser {
    @Getter
    @NoArgsConstructor
    public static class Request {
        @Email
        @NotBlank(message = "이메일은 비어있을 수 없습니다.")
        private String email;
        @NotBlank(message = "패스워드는 비어있을 수 없습니다.")
        private String password;
        @NotBlank(message = "닉네임은 비어있을 수 없습니다.")
        private String nickname;
    }

    @Getter
    public static class Response {
        private Long id;
        private String email;
        private String nickname;

        private Response(Long id, String email, String nickname) {
            this.id = id;
            this.email = email;
            this.nickname = nickname;
        }
    }

    public static Response toResponse(User user) {
        return new Response(user.getId(), user.getEmail(), user.getNickname());
    }
}
