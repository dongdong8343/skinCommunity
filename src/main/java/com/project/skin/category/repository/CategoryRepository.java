package com.project.skin.category.repository;

import com.project.skin.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE (:parentId IS NULL AND c.parent IS NULL) OR (c.parent.id = :parentId) AND c.deletedAt IS NULL")
    List<Category> findByParentIdNullable(@Param("parentId") Long parentId);

    Optional<Category> findByCode(String code);
}
