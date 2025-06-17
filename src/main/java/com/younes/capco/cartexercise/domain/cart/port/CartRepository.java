package com.younes.capco.cartexercise.domain.cart.port;

import com.younes.capco.cartexercise.domain.cart.model.Cart;
import java.util.Optional;

public interface CartRepository {
    void save(Cart cart);
    Optional<Cart> findById(String clientId);
}
