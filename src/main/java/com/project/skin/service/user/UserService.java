package com.project.skin.service.user;

import com.project.skin.config.error.exception.DuplicateEmailException;
import com.project.skin.config.error.exception.DuplicateNicknameException;
import com.project.skin.domain.user.Role;
import com.project.skin.domain.user.User;
import com.project.skin.domain.user.UserRole;
import com.project.skin.provider.UserProvider;
import com.project.skin.service.dto.AddUser;
import com.project.skin.service.validator.CreateUserValidate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

        Optional<User> user = userProvider.checkEmail(email);
        if(user.isPresent()) {
            throw new DuplicateEmailException();
        }
    }

    @Transactional(readOnly = true)
    public boolean checkNickname(String nickname) {
        return userProvider.checkNickname(nickname);
    }

    public User createUser(AddUser.Request request) {

        createUserValidate.validate(request);
        User user = User.createBasicUser(request.getEmail(), bCryptPasswordEncoder.encode(request.getPassword()), request.getNickname());


        return userProvider.createUser(user);
    }

    public void grantAdminUser(Long userId) {
        User user = userProvider.loadUserById(userId);
        UserRole userRole = UserRole.ofUserRole(user, Role.ADMIN);

        userProvider.grantAdminUser(userRole);
    }
}


