package com.project.skin.repository;

import com.project.skin.domain.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "userRoles", type = EntityGraph.EntityGraphType.FETCH)
    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);
}
