package com.molka.boutiqueenlinge1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.molka.boutiqueenlinge1.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}