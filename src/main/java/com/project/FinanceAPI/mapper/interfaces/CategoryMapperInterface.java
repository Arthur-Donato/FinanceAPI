package com.project.FinanceAPI.mapper.interfaces;

import com.project.FinanceAPI.DTOs.request.CategoryRequestDTO;
import com.project.FinanceAPI.DTOs.response.CategoryResponseDTO;
import com.project.FinanceAPI.model.entities.Category;
import com.project.FinanceAPI.model.entities.User;

import java.util.List;

public interface CategoryMapperInterface {

    Category toCategory(CategoryRequestDTO dto, User user);

    CategoryResponseDTO toResponseDTO(Category entity);

    List<CategoryResponseDTO> toResponseDTOList(List<Category> entities);
}
