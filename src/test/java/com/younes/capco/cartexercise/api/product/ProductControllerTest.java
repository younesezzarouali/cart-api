package com.younes.capco.cartexercise.api.product;

import com.younes.capco.cartexercise.domain.product.model.Product;
import com.younes.capco.cartexercise.domain.product.model.ProductType;
import com.younes.capco.cartexercise.domain.product.port.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ProductRepository productRepository;

    @Test
    void shouldCreateProduct() throws Exception {
        // Given
        String json = "{\"type\":\"LAPTOP\",\"quantity\":5}";
        // When / Then
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isCreated());
        then(productRepository).should().save(any(Product.class));
    }

    @Test
    void shouldGetProductWhenExists() throws Exception {
        // Given
        Product product = new Product(ProductType.MID_RANGE_PHONE,3);
        given(productRepository.findByType(ProductType.MID_RANGE_PHONE)).willReturn(java.util.Optional.of(product));
        // When / Then
        mockMvc.perform(get("/api/products/MID_RANGE_PHONE"))
            .andExpect(status().isOk())
            .andExpect(content().json(jsonOf(product)));
    }

    @Test
    void shouldReturn404WhenProductNotFound() throws Exception {
        // Given
        given(productRepository.findByType(ProductType.HIGH_END_PHONE)).willReturn(java.util.Optional.empty());
        // When / Then
        mockMvc.perform(get("/api/products/HIGH_END_PHONE"))
            .andExpect(status().isNotFound());
    }

    // Utilitaire pour sérialiser un objet en JSON
    private String jsonOf(Object obj) {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

