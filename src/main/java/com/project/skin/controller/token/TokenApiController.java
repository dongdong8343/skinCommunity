package com.project.skin.controller.token;

import com.project.skin.service.dto.CreateAccessToken;
import com.project.skin.service.token.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/token")
@RestController
@Log4j2
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping
    public CreateAccessToken.Response createNewAccessToken(@RequestBody CreateAccessToken.Request request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return new CreateAccessToken.Response(newAccessToken);
    }
}
