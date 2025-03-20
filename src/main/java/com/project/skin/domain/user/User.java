package com.project.skin.domain.user;

import com.project.skin.domain.BaseTimeEntity;
import com.project.skin.domain.post.Comment;
import com.project.skin.domain.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Enumerated(EnumType.STRING)
   @Column(length = 30, nullable = false)
   private LoginType type;

   @Column(length = 40, nullable = false)
   private String email;

   @Column(length = 100)
   private String password;

   @Column(length = 20, nullable = false)
   private String nickname;


   @OneToMany
   private List<UserRole> userRoles;


   public User(LoginType type, String email, String password, String nickname) {
      this.type = type;
      this.email = email;
      this.password = password;
      this.nickname = nickname;
   }

   public static User create(LoginType type, String email, String password, String name, String nickname) {
      return new User(type, email, password, nickname);
   }

   public void updateUser(String password, String nickname) {
      if (password != null) {
         this.password = password;
      }

      if (nickname != null) {
         this.nickname = nickname;
      }
   }
}
