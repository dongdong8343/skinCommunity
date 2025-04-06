package com.project.skin.repository;

import com.project.skin.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u JOIN fetch u.userRoles where u.email = :email")
    Optional<User> findByEmailWithUserRoles(@Param("email") String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);
}
