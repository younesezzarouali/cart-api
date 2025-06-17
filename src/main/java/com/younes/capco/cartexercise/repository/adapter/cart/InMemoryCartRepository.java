package com.younes.capco.cartexercise.repository.adapter.cart;

import com.younes.capco.cartexercise.domain.cart.model.Cart;
import com.younes.capco.cartexercise.domain.cart.port.CartRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCartRepository implements CartRepository {
    private final Map<String, Cart> store = new ConcurrentHashMap<>();

    @Override
    public void save(Cart cart) {
        store.put(cart.client().id(), cart);
    }

    @Override
    public Optional<Cart> findById(String clientId) {
        return Optional.ofNullable(store.get(clientId));
    }
}
