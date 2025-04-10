package com.project.skin.service.validator;

import com.project.skin.config.error.exception.DuplicateEmailException;
import com.project.skin.config.error.exception.DuplicateNicknameException;
import com.project.skin.provider.user.UserProvider;
import com.project.skin.service.dto.AddUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateUserValidate {
    private final UserProvider userProvider;

    public void validate(AddUser.Request request) {
        checkEmail(request.getEmail());
        checkNickname(request.getNickname());
    }

    public void checkEmail(String email) {
        if (userProvider.checkEmail(email).isPresent()) {
            throw new DuplicateEmailException();
        }
    }

    public void checkNickname(String nickname) {
        if (userProvider.checkNickname(nickname).isPresent()) {
            throw new DuplicateNicknameException();
        }
    }
}
