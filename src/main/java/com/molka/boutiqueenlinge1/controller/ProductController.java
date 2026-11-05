package com.molka.boutiqueenlinge1.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.molka.boutiqueenlinge1.model.Product;
import com.molka.boutiqueenlinge1.repository.ProductRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> afficherProduits() {
        return productRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Product afficherProduitParId(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void supprimerProduit(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
    

    @PostMapping
    public Product ajouterProduit(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }
    @PutMapping("/{id}")
    public Product modifierProduit(
            @PathVariable Long id,
            @Valid @RequestBody Product product) {
        product.setId(id);
        return productRepository.save(product);
    }
@GetMapping("/search")
public List<Product> rechercherParNom(
        @RequestParam String name) {
    return productRepository.findByNameContainingIgnoreCase(name);
}

@GetMapping("/category/{categoryId}")
public List<Product> rechercherParCategorie(
        @PathVariable Long categoryId) {
    return productRepository.findByCategory_Id(categoryId);
}

}