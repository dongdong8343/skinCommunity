package com.project.skin;

import com.project.skin.CreatePost.Request;
import com.project.skin.domain.category.Category;
import com.project.skin.domain.post.Comment;
import com.project.skin.domain.post.File;
import com.project.skin.domain.post.Post;
import com.project.skin.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.IllformedLocaleException;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

   private final PostService postService;

   @PostMapping
   public void createPost(@RequestBody CreatePost.Request request, User user) {
      postService.createPost(request, user);
   }

   @PatchMapping("/{postId}")
   public void updatePost(@PathVariable Long postId, @RequestBody UpdatePost.Request request, User user) {
      postService.updatePost(postId, request, user);
   }
}

@RequiredArgsConstructor
@Service
class PostService {

   private final PostValidator postValidator;

   @Transactional
   public void createPost(CreatePost.Request request, User user) {

      Category category = categoryDataprovider.findCategoryByCategoryId(request.getCategoryId());

      // 로직의 순서
      Post post = Post.create(user.getId(), category.getCategoryId(), request.getTitle(), request.getContent(),
         request.getFiles());
      postDateprovider.save(post);
   }

   @Transactional
   public void updatePost(Long postId, UpdatePost.Request request, User user) {

      // 1. 유저를 찾는다.
      // 2. 포스트를 찾는다.
      // 3. update 가능 validation 체크.
      // 4. 카테고리 가져오기
      // 5. 업데이트

      User user = userDataprovider.findUserById(user.getId());
      Post post = postDataprovider.findPostById(postId);

      postValidator.updateValidate(post, user);
      // 3

      Category category = categoryDataprovider.findCategoryByCategoryId(request.getCategoryId());

      post.update(request.getTitle(), request.getContent(), category.getCategoryId());
   }
}

@Component
class PostValidator {

   // 이게시글이 내꺼인가.
   public void updateValidate(Post post, User user) {

     이글이내꺼인가()
     등록이가능한가()
     삭제가가능한가()
     내 가족들도 수정할수 있는가 ()
   }

   public void deleteValidate(Post post, User user) {
      이글이내꺼인가();
   }

   private void 이글이_내꺼인가(Post post, User user) {

      if(post.isMine(user)) {
         return;
      }

      throw new IllformedLocaleException();
   }
}


@NoArgsConstructor(access = AccessLevel.PRIVATE)
class CreatePost {

   @Getter
   public static class Request {

      private Long categoryId;
      private String title;
      private String content;
      private List<File> files = new ArrayList<>();
   }
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UpdatePost {

   @Getter
   public static class Request {

      private Long categoryId;
      private String title;
      private String content;
      private List<File> files = new ArrayList<>();
   }
}
