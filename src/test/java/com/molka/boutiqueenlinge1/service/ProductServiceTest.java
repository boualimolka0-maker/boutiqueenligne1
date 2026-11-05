
package com.molka.boutiqueenlinge1.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.molka.boutiqueenlinge1.exception.*;
import com.molka.boutiqueenlinge1.model.Product;
import com.molka.boutiqueenlinge1.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    // TEST 1 : Récupérer un produit par ID
    @Test
    void testGetProductById() {

        Product product = new Product(
            "Ordinateur HP",
            "Ordinateur portable",
            1500.0,
            10
        );

        when(repository.findById(1L))
            .thenReturn(Optional.of(product));

        Product result = service.getProductById(1L);

        assertNotNull(result);
        assertEquals("Ordinateur HP", result.getName());
        assertEquals(1500.0, result.getPrice());

    }

    // TEST 2 : Produit introuvable
    @Test
    void testProductNotFound() {

        when(repository.findById(99L))
            .thenReturn(Optional.empty());

        assertThrows(
            ResourceNotFoundException.class,
            () -> service.getProductById(99L)
        );

    }

    // TEST 3 : Ajouter un produit
    @Test
    void testCreateProduct() {

        Product product = new Product(
            "Clavier",
            "Clavier sans fil",
            80.0,
            20
        );

        when(repository.save(product)).thenReturn(product);

        Product result = service.createProduct(product);

        assertNotNull(result);
        assertEquals("Clavier", result.getName());

        verify(repository, times(1)).save(product);

    }

    // TEST 4 : Modifier un produit
    @Test
    void testUpdateProduct() {

        Product oldProduct = new Product(
            "Ancien PC",
            "Ancienne description",
            1000.0,
            5
        );

        Product newProduct = new Product(
            "Nouveau PC",
            "Nouvelle description",
            1800.0,
            10
        );

        when(repository.findById(1L))
            .thenReturn(Optional.of(oldProduct));

        when(repository.save(oldProduct))
            .thenReturn(oldProduct);

        Product result = service.updateProduct(1L, newProduct);

        assertEquals("Nouveau PC", result.getName());
        assertEquals("Nouvelle description", result.getDescription());
        assertEquals(1800.0, result.getPrice());
        assertEquals(10, result.getQuantity());

        verify(repository).save(oldProduct);

    }

    // TEST 5 : Supprimer un produit
    @Test
    void testDeleteProduct() {

        Product product = new Product(
            "Souris",
            "Souris USB",
            30.0,
            15
        );

        when(repository.findById(1L))
            .thenReturn(Optional.of(product));

        service.deleteProduct(1L);

        verify(repository, times(1)).delete(product);

    }
}
