package com.project.FinanceAPI.controllers;

import com.project.FinanceAPI.DTOs.request.CategoryRequestDTO;
import com.project.FinanceAPI.DTOs.response.CategoryResponseDTO;
import com.project.FinanceAPI.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users/{userId}/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Validated CategoryRequestDTO categoryRequestDTO, @PathVariable(value = "userId") UUID userId) {
        CategoryResponseDTO categoryCreatedDto = this.categoryService.createCategory(userId, categoryRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryCreatedDto);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategoriesByUser(@PathVariable(value = "userId") UUID userId) {
        List<CategoryResponseDTO> categories = this.categoryService.getAllCategoriesByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping(path = "/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getCategoryByUserIdAndId(@PathVariable(value = "userId") UUID userId, @PathVariable(value = "categoryId") UUID categoryId ) {
        CategoryResponseDTO category = this.categoryService.getCategoryById(userId, categoryId);

        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @PutMapping(path ="/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable(value = "userId") UUID userId, @PathVariable(value = "categoryId") UUID categoryId,
                                                            @RequestBody @Validated CategoryRequestDTO updateRequestDto) {

        CategoryResponseDTO categoryUpdatedDto = this.categoryService.updateCategory(userId, categoryId, updateRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(categoryUpdatedDto);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(value = "userId") UUID userId, @PathVariable(value = "categoryId") UUID categoryId) {

        this.categoryService.deleteCategoryById(userId, categoryId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
