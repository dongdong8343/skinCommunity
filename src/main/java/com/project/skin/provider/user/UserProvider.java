package com.project.skin.provider.user;

import com.project.skin.config.error.exception.UserNotFoundException;
import com.project.skin.domain.user.User;
import com.project.skin.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Component
public class UserProvider {
    private final UserRepository userRepository;

    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public User loadUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public Optional<User> checkEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> checkNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}

