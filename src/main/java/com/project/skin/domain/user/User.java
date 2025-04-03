package com.project.skin.domain.user;

import com.project.skin.domain.BaseTimeEntity;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity implements UserDetails { // 인증 객체로 사용
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserRole> userRoles;

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
        user.addRole(UserRole.ofUserRole(user, Role.USER));
        return user;
    }

    private void addRole(UserRole userRole) {
        this.userRoles.add(userRole);
    }

    public void updateUser(String password, String nickname) {

        if(StringUtils.isNotBlank(password)) {

            // AAA == AAA
            this.password = password;
        }


        if(StringUtils.isNotBlank(nickname)) {

            // 최윤진 == 최윤진
            this.nickname = nickname;
        }
    }

    @Override // 계정 만료 여부 반환
    public boolean isAccountNonExpired() {
        return true; // true -> 만료 x
    }

    @Override // 계정 잠금 여부 반환
    public boolean isAccountNonLocked() {
        return true; // true -> 만료 x
    }

    @Override // 패스워드 만료 여부 반환
    public boolean isCredentialsNonExpired() {
        return true; // true -> 만료 x
    }

    @Override // 계정 사용 여부 반환
    public boolean isEnabled() {
        return true; // true -> 만료 x
    }

    @Override // 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().getKey()))
                .collect(Collectors.toList());
    }

    @Override // 사용자 id 반환
    public String getUsername() {
        return this.email;
    }

}