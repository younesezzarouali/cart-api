package com.younes.capco.cartexercise.repository.adapter.product;

import com.younes.capco.cartexercise.domain.product.model.Product;
import com.younes.capco.cartexercise.domain.product.model.ProductType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

public class InMemoryProductRepositoryTest {

    private InMemoryProductRepository repo = new InMemoryProductRepository();

    @Test
    void shouldSaveAndFindProduct() {
        // Given
        Product p = new Product(ProductType.LAPTOP, 10);
        // When
        repo.save(p);
        Optional<Product> found = repo.findByType(ProductType.LAPTOP);
        // Then
        assertTrue(found.isPresent());
        assertEquals(p, found.get());
    }

    @Test
    void shouldReturnEmptyWhenProductNotFound() {
        // Given no product saved
        // When
        Optional<Product> found = repo.findByType(ProductType.HIGH_END_PHONE);
        // Then
        assertFalse(found.isPresent());
    }
}

