package com.project.skin.service.user;

import com.project.skin.domain.user.Role;
import com.project.skin.domain.user.User;
import com.project.skin.provider.UserProvider;
import com.project.skin.service.dto.AddUser;
import com.project.skin.service.validator.CreateUserValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserProvider userProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CreateUserValidate createUserValidate;

    @Transactional(readOnly = true)
    public void checkEmail(String email) {
        createUserValidate.checkEmail(email);
    }

    @Transactional(readOnly = true)
    public void checkNickname(String nickname) {
        createUserValidate.checkNickname(nickname);
    }

    public User createUser(AddUser.Request request) {
        createUserValidate.validate(request);

        String encryptedPassword = bCryptPasswordEncoder.encode(request.getPassword());

        User user = User.createBasicUser(request.getEmail(), encryptedPassword, request.getNickname());

        return userProvider.createUser(user);
    }

    @Transactional
    public void grantRole(Long userId, String role) {
        User user = userProvider.loadUserById(userId);
        user.grantRole(Role.valueOf("ROLE_" + role));
    }
}


