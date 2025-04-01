package com.project.skin.controller.user;

import com.project.skin.domain.user.User;
import com.project.skin.service.UserService;
import com.project.skin.service.dto.AddUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserApiController {
    private final UserService userService;

    @GetMapping("/check-email")
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam(value = "email") String email) {
        System.out.println(email);
        Map<String, Boolean> response = new HashMap<>();

        response.put("exists", userService.checkEmail(email));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/check-nickname")
    public ResponseEntity<Map<String, Boolean>> checkNickname(@RequestParam(value = "nickname") String nickname) {
        System.out.println(nickname);
        Map<String, Boolean> response = new HashMap<>();

        response.put("exists", userService.checkNickname(nickname));

        return ResponseEntity.ok(response);
    }

    // 해당 경로 시큐리티 설정에서 모든 권한에서 접근 가능하게 허용 안해서 2일 날림....
    @PostMapping("/new")
    public AddUser.Response signup(@RequestBody AddUser.Request request) {
        User user = userService.createUser(request);

        return AddUser.Response.toResponse(user);
    }

    @PostMapping("/grant/{userId}")
    public ResponseEntity<String> grantAdmin(@PathVariable Long userId) {
        userService.grantAdminUser(userId);

        return ResponseEntity.ok("관리자 권한이 부여됐습니다.");
    }
}
