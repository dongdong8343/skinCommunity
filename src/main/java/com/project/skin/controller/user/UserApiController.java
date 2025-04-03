package com.project.skin.controller.user;

import com.project.skin.domain.user.User;
import com.project.skin.service.user.UserService;
import com.project.skin.service.dto.AddUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserApiController {
    private final UserService userService;

    @GetMapping("/check-email")
    public void checkEmail(@RequestParam(value = "email") String email) {
       log.info("===========================> email = " + email);
        userService.checkEmail(email);
    }

    @GetMapping("/check-nickname")
    public ResponseEntity<Map<String, Boolean>> checkNickname(@RequestParam(value = "nickname") String nickname) {
        Map<String, Boolean> response = new HashMap<>();

        response.put("exists", userService.checkNickname(nickname));

        return ResponseEntity.ok(response);
    }


    // RestApi 잘 모른다

    //  POST {host}/api/v1/users
    @PostMapping
    public AddUser.Response signup(@RequestBody AddUser.Request request) {
        User user = userService.createUser(request);

        return AddUser.Response.toResponse(user);
    }

    @PostMapping("/{userId}/roles/{role}")
    public ResponseEntity<String> grantAdmin(@PathVariable Long userId) {
        userService.grantAdminUser(userId);

        return ResponseEntity.ok("관리자 권한이 부여됐습니다.");
    }
}
