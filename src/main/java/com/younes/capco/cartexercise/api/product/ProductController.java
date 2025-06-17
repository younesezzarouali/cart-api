package com.younes.capco.cartexercise.api.product;

import com.younes.capco.cartexercise.domain.product.model.Product;
import com.younes.capco.cartexercise.domain.product.model.ProductType;
import com.younes.capco.cartexercise.domain.product.port.ProductRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

    @GetMapping("/{type}")
    public Product getProduct(@PathVariable ProductType type) {
        return productRepository.findByType(type)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
