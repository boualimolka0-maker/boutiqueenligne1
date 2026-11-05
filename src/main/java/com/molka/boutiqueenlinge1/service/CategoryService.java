
package com.molka.boutiqueenlinge1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.molka.boutiqueenlinge1.model.Category;
import com.molka.boutiqueenlinge1.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    
    public Category getCategoryById(Long id) {
        return repository.findById(id).orElse(null);
    }

    
    public Category createCategory(Category category) {
        return repository.save(category);
    }

    
    public Category updateCategory(Long id, Category nouvelleCategory) {
        return repository.findById(id).map(category -> {
            category.setName(nouvelleCategory.getName());
            category.setDescription(nouvelleCategory.getDescription());

            return repository.save(category);
        }).orElse(null);
    }

    
    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }
}
