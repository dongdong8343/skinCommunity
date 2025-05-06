package com.project.skin.category.repository;

import com.project.skin.category.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // 코드 기반으로 카테고리 찾아오는 메서드
    Optional<Category> findByCode(String code);

    // 카테고리 이름이 날라오면 db에 저장된 카테고리가 있는지 판단


}
