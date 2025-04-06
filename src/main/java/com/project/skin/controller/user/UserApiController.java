package com.project.skin.controller.user;

import com.project.skin.domain.user.User;
import com.project.skin.service.user.UserService;
import com.project.skin.service.dto.AddUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserApiController {
    private final UserService userService;


    @GetMapping("/check-email")
    public void checkEmail(@RequestParam(value = "email") String email) {
        userService.checkEmail(email);
    }

    @GetMapping("/check-nickname")
    public void checkNickname(@RequestParam(value = "nickname") String nickname) {
        userService.checkNickname(nickname);
    }

    @PostMapping
    public AddUser.Response signup(@RequestBody AddUser.Request request) {
        User user = userService.createUser(request);

        return AddUser.Response.toResponse(user);
    }

    @PostMapping("{userId}/role/{role}")
    public ResponseEntity<String> grantRole(@PathVariable Long userId, @PathVariable String role) {
        userService.grantRole(userId, role);

        return ResponseEntity.ok("관리자 권한이 부여됐습니다.");
    }
}
