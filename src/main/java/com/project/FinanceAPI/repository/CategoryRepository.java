package com.project.FinanceAPI.repository;

import com.project.FinanceAPI.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findByUserIdAndName(UUID userId, String name);

    List<Category> findAllByUserId(UUID userId);

    boolean existsByUserIdAndName(UUID userId, String name);

    boolean existsByUserIdAndIdNotAndName(UUID userId, UUID categoryId, String name);

    Optional<Category> findByUserIdAndId(UUID userId, UUID categoryId);
}
