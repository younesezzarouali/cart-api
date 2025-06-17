package com.younes.capco.cartexercise.repository.adapter.product;

import com.younes.capco.cartexercise.domain.product.model.Product;
import com.younes.capco.cartexercise.domain.product.model.ProductType;
import com.younes.capco.cartexercise.domain.product.port.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final Map<ProductType, Product> store = new ConcurrentHashMap<>();

    @Override
    public void save(Product product) {
        store.put(product.type(), product);
    }

    @Override
    public Optional<Product> findByType(ProductType type) {
        return Optional.ofNullable(store.get(type));
    }
}
