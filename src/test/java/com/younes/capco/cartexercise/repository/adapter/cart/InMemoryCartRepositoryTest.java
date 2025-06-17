package com.younes.capco.cartexercise.repository.adapter.cart;

import com.younes.capco.cartexercise.domain.cart.model.Cart;
import com.younes.capco.cartexercise.domain.cart.model.CartItem;
import com.younes.capco.cartexercise.domain.client.model.IndividualClient;
import com.younes.capco.cartexercise.domain.product.model.ProductType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;

public class InMemoryCartRepositoryTest {

    private InMemoryCartRepository repo = new InMemoryCartRepository();

    @Test
    void shouldSaveAndFindCart() {
        // Given
        Cart cart = new Cart(new IndividualClient("id1","John","Doe"),
                             List.of(new CartItem(ProductType.LAPTOP,2)));
        // When
        repo.save(cart);
        Optional<Cart> found = repo.findById("id1");
        // Then
        assertTrue(found.isPresent());
        assertEquals(cart, found.get());
    }

    @Test
    void shouldReturnEmptyWhenCartNotFound() {
        // Given no cart saved
        // When
        Optional<Cart> found = repo.findById("none");
        // Then
        assertFalse(found.isPresent());
    }
}

