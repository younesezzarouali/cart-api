package com.younes.capco.cartexercise.domain.product.port;

import com.younes.capco.cartexercise.domain.product.model.Product;
import com.younes.capco.cartexercise.domain.product.model.ProductType;
import java.util.Optional;

public interface ProductRepository {
    void save(Product product);
    Optional<Product> findByType(ProductType type);
}
