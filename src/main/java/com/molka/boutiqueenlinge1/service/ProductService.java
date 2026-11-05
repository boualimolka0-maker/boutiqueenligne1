package com.molka.boutiqueenlinge1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.molka.boutiqueenlinge1.exception.*;
import com.molka.boutiqueenlinge1.model.*;
import com.molka.boutiqueenlinge1.repository.*;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Produit introuvable avec ID : " + id
            ));
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Long id, Product newProduct) {

        Product product = getProductById(id);

        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setPrice(newProduct.getPrice());
        product.setQuantity(newProduct.getQuantity());
        product.setCategory(newProduct.getCategory());

        return repository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        repository.delete(product);
    }
}
