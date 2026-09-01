package com.project.FinanceAPI.mapper.implementations;

import com.project.FinanceAPI.DTOs.request.CategoryRequestDTO;
import com.project.FinanceAPI.DTOs.response.CategoryResponseDTO;
import com.project.FinanceAPI.mapper.interfaces.CategoryMapperInterface;
import com.project.FinanceAPI.model.entities.Category;
import com.project.FinanceAPI.model.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper implements CategoryMapperInterface {


    @Override
    public Category toCategory(CategoryRequestDTO dto, User user) {
        if(dto == null) {
            return null;
        }

        return Category.builder()
                .name(dto.name())
                .user(user)
                .build();
    }

    @Override
    public CategoryResponseDTO toResponseDTO(Category entity) {
        if(entity == null) {
            return null;
        }

        return new CategoryResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getUser().getId(),
                entity.getCreated_at()
        );
    }

    @Override
    public List<CategoryResponseDTO> toResponseDTOList(List<Category> entities) {
        if(entities == null){
            return null;
        }

        return entities.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
