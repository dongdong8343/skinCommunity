package com.project.skin.service.dto;
 import com.project.skin.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AddUser {
    @Getter
    @NoArgsConstructor
    public static class Request {
        private String email;
        private String password;
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

        public static Response toResponse(User user) {
            return new Response(user.getId(), user.getEmail(), user.getNickname());
        }
    }
}
