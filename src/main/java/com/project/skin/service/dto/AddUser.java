package com.project.skin.service.dto;
import lombok.Getter;

public class AddUser {
    @Getter
    public static class Request {
        private String email;
        private String password;
        private String nickname;

        private Request(String email, String password, String nickname) {
            this.email = email;
            this.password = password;
            this.nickname = nickname;
        }

        public static Request of(String email, String password, String nickname) {
            return new Request(email, password, nickname);
        }
    }
}
