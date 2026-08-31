package com.project.FinanceAPI.repository;

import com.project.FinanceAPI.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findByNameAndUserId(String name, UUID userID);

    List<Category> findAllByUserId(UUID userId);

    boolean existsByNameAndUserId(String name, UUID userId);

}
