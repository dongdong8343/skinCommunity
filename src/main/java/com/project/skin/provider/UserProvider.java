package com.project.skin.provider;

import com.project.skin.domain.user.User;
import com.project.skin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProvider {
    private final UserRepository userRepository;

    public UserDetails loadUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(IllegalArgumentException::new);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
