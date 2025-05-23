package com.project.skin.user.controller;

import com.project.skin.auth.jwt.dto.TokenType;
import com.project.skin.user.dto.Login;
import com.project.skin.user.entity.UserRole;
import com.project.skin.user.service.UserService;
import com.project.skin.user.dto.AddUser;
import com.project.skin.auth.jwt.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserResource {
    private final UserService userService;
    private final CookieUtil cookieUtil;

    @GetMapping("/check-email")
    public void checkEmail(@RequestParam(value = "email") String email) {
        userService.checkEmail(email);
    }

    @GetMapping("/check-nickname")
    public void checkNickname(@RequestParam(value = "nickname") String nickname) {
        userService.checkNickname(nickname);
    }

    @PostMapping
    public AddUser.Response signup(@RequestBody @Valid AddUser.Request request) {
        return userService.createUser(request);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login.Request request, HttpServletResponse response) {
        Login.Response loginResponse = userService.login(request);

        response.addCookie(
                cookieUtil.createCookie(
                        TokenType.ACCESS_TOKEN.getTokenType(), loginResponse.getAccessToken(), loginResponse.getAccessTokenCookieMaxAge()
                ));

        response.addCookie(
                cookieUtil.createCookie(
                        TokenType.REFRESH_TOKEN.getTokenType(), loginResponse.getRefreshToken(), loginResponse.getRefreshTokenCookieMaxAge()
                ));

        return ResponseEntity.ok("로그인 성공");
    }

    @PostMapping("{userId}/role/{role}")
    public ResponseEntity<String> grantRole(@PathVariable Long userId, @PathVariable UserRole role) {
        userService.grantRole(userId, role);

        return ResponseEntity.ok("관리자 권한이 부여됐습니다.");
    }
}
