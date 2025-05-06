package com.project.skin.user.entities;

import com.project.skin.global.base.BaseTimeEntity;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoginType type;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<UserRole> userRoles = new ArrayList<>();

    private User(LoginType type, String email, String password, String nickname) {
        this.type = type;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public static User create(LoginType type, String email, String password, String nickname) {
        return new User(type, email, password, nickname);
    }

    public static User createBasicUser(String email, String password, String nickname) {
        User user = create(LoginType.BASIC, email, password, nickname);
        user.addRole(UserRole.ofUserRole(Role.USER));
        return user;
    }

    public void grantRole(Role role) {
        this.addRole(UserRole.ofUserRole(role));
    }

    private void addRole(UserRole userRole) {
        this.userRoles.add(userRole);
    }

    public void updateUser(String password, String nickname) {
        if(StringUtils.isNotBlank(password)) {
            this.password = password;
        }

        if(StringUtils.isNotBlank(nickname)) {
            this.nickname = nickname;
        }
    }
}