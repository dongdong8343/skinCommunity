package com.project.skin.service;

import com.project.skin.domain.user.Role;
import com.project.skin.domain.user.User;
import com.project.skin.domain.user.UserRole;
import com.project.skin.provider.UserProvider;
import com.project.skin.service.dto.AddUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserProvider userProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean checkEmail(String email) {
        return userProvider.checkEmail(email);
    }

    public boolean checkNickname(String nickname) {
        return userProvider.checkNickname(nickname);
    }

    public User createUser(AddUser.Request request) {
        String encryptedPassword = bCryptPasswordEncoder.encode(request.getPassword());

        if(userProvider.checkEmail(request.getEmail())) {
            throw new IllegalArgumentException();
        }

        User user = User.createBasicUser(request.getEmail(), encryptedPassword, request.getNickname());
        UserRole userRole = UserRole.ofUserRole(user, Role.USER);

        return userProvider.createUser(user, userRole);
    }

    public void grantAdminUser(Long userId) {
        User user = userProvider.loadUserById(userId);
        UserRole userRole = UserRole.ofUserRole(user, Role.ADMIN);

        userProvider.grantAdminUser(userRole);
    }
}


