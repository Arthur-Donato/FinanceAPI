package com.project.FinanceAPI.services;

import com.project.FinanceAPI.DTOs.request.CategoryRequestDTO;
import com.project.FinanceAPI.DTOs.response.CategoryResponseDTO;
import com.project.FinanceAPI.exceptions.DuplicationResourceException;
import com.project.FinanceAPI.exceptions.ResourceNotFoundException;
import com.project.FinanceAPI.mapper.implementations.CategoryMapper;
import com.project.FinanceAPI.model.entities.Category;
import com.project.FinanceAPI.model.entities.User;
import com.project.FinanceAPI.repository.CategoryRepository;
import com.project.FinanceAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    public CategoryResponseDTO createCategory(UUID userId, CategoryRequestDTO categoryRequestDTO) {
        User user = this.getUserEntityById(userId);

        if(this.categoryRepository.existsByUserIdAndName(userId, categoryRequestDTO.name())) {
            throw new DuplicationResourceException("This user already have one category with this name.");
        }

        Category category = this.categoryMapper.toCategory(categoryRequestDTO, user);

        Category categorySaved = this.categoryRepository.save(category);

        return this.categoryMapper.toResponseDTO(categorySaved);
    }

    public List<CategoryResponseDTO> getAllCategoriesByUserId(UUID userId) {
        List<Category> categories = this.categoryRepository.findAllByUserId(userId);

        return this.categoryMapper.toResponseDTOList(categories);
    }

    public CategoryResponseDTO getCategoryById(UUID userId, UUID categoryId) {
        Category category = this.getCategoryEntityByUserIdAndId(userId, categoryId);

        return this.categoryMapper.toResponseDTO(category);
    }

    public CategoryResponseDTO getCategoryByUserIdAndName(UUID userId, String name) {
        Category category = this.getCategoryEntityByUserIdAndName(userId, name);

        return this.categoryMapper.toResponseDTO(category);
    }

    public CategoryResponseDTO updateCategory(UUID userId, UUID categoryId, CategoryRequestDTO updateRequestDto) {
        Category category = this.getCategoryEntityByUserIdAndId(userId, categoryId);

        if(this.categoryRepository.existsByUserIdAndIdNotAndName(userId, categoryId, updateRequestDto.name())) {
            throw new DuplicationResourceException("This user already have one category with this name.");
        }

        category.setName(updateRequestDto.name());

        Category categoryUpdated = this.categoryRepository.save(category);

        return this.categoryMapper.toResponseDTO(categoryUpdated);
    }


    public void deleteCategoryById(UUID userId, UUID categoryId) {
        Category category = this.getCategoryEntityByUserIdAndId(userId, categoryId);

        this.categoryRepository.delete(category);
    }

    private User getUserEntityById(UUID userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    private Category getCategoryEntityByUserIdAndId(UUID userId, UUID categoryId) {
        return this.categoryRepository.findByUserIdAndId(userId, categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found."));
    }

    private Category getCategoryEntityByUserIdAndName(UUID userId, String name) {
        return this.categoryRepository.findByUserIdAndName(userId, name)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with these parameters."));
    }
}
