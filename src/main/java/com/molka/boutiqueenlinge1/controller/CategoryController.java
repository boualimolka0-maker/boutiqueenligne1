package com.molka.boutiqueenlinge1.controller;

import org.springframework.web.bind.annotation.*;

import com.molka.boutiqueenlinge1.model.Category;
import com.molka.boutiqueenlinge1.repository.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    
    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public Category updateCategory(
            @PathVariable Long id,
            @RequestBody Category nouvelleCategory) {

        return categoryRepository.findById(id).map(category -> {
            category.setName(nouvelleCategory.getName());
            category.setDescription(nouvelleCategory.getDescription());

            return categoryRepository.save(category);
        }).orElse(null);
    }
}