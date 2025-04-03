package com.project.skin.provider;

import com.project.skin.config.error.exception.UserNotFoundException;
import com.project.skin.domain.user.User;
import com.project.skin.domain.user.UserRole;
import com.project.skin.repository.UserRepository;
import com.project.skin.repository.UserRoleRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class UserProvider {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public UserDetails loadUserByEmail(String email) {
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

    public boolean checkNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    public void grantAdminUser(UserRole userRole) {
        userRoleRepository.save(userRole);
    }
}

