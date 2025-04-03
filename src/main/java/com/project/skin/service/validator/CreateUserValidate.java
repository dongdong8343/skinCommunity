package com.project.skin.service.validator;

import com.project.skin.config.error.exception.DuplicateEmailException;
import com.project.skin.config.error.exception.DuplicateNicknameException;
import com.project.skin.provider.UserProvider;
import com.project.skin.service.dto.AddUser;
import com.project.skin.service.dto.AddUser.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateUserValidate {

   private final UserProvider userProvider;

   public void validate(AddUser.Request request) {

      checkAvailableEmail(request.getEmail());
      checkAvailableNickName(request.getNickname());
   }

   private void checkAvailableNickName(String nickname) {

      if (userProvider.checkNickname(nickname)) {
         throw new DuplicateNicknameException();
      }
   }

   private void checkAvailableEmail(String email) {

      if(userProvider.checkEmail(email).isPresent()) {
         throw new DuplicateEmailException();
      }
   }
}
