
package com.molka.boutiqueenlinge1.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.molka.boutiqueenlinge1.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    
    List<Product> findByNameContainingIgnoreCase(String name);

    
    List<Product> findByCategory_Id(Long categoryId);
}
