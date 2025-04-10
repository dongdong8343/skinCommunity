package com.project.skin.service.user;

import com.project.skin.domain.user.Role;
import com.project.skin.domain.user.User;
import com.project.skin.provider.user.UserProvider;
import com.project.skin.service.dto.AddUser;
import com.project.skin.service.dto.EmailMessage;
import com.project.skin.service.email.EmailService;
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
    private final EmailService emailService;

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

        User savedUser = userProvider.createUser(user);

        emailService.sendSignupSuccessMail(
                EmailMessage.createEmailMessage(
                        savedUser.getEmail(),
                        "스킨로그 회원가입을 축하드립니다!",
                        savedUser.getNickname() + "님 회원가입을 축하드립니다."),
                user.getNickname());

        return savedUser;
    }

    @Transactional
    public void grantRole(Long userId, String role) {
        User user = userProvider.loadUserById(userId);
        user.grantRole(Role.valueOf("ROLE_" + role));
    }
}


