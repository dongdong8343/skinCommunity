package com.project.skin.service;

import com.project.skin.domain.user.User;
import com.project.skin.provider.UserProvider;
import com.project.skin.service.dto.AddUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserProvider userProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUser.Request request) {
        String encryptedPassword = bCryptPasswordEncoder.encode(request.getPassword());

        User user = userProvider.save(User.createBasicUser(request.getEmail(), encryptedPassword, request.getNickname()));

        return user.getId();
    }
}


