package com.younes.capco.cartexercise.domain.cart;

import com.younes.capco.cartexercise.domain.cart.CartService;
import com.younes.capco.cartexercise.domain.cart.model.Cart;
import com.younes.capco.cartexercise.domain.cart.model.CartItem;
import com.younes.capco.cartexercise.domain.client.model.IndividualClient;
import com.younes.capco.cartexercise.domain.product.model.ProductType;
import com.younes.capco.cartexercise.domain.product.PricingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock private PricingService pricingService;
    @InjectMocks private CartService cartService;

    @Test
    void shouldReturnZeroForEmptyCart() {
        // Given
        Cart cart = new Cart(new IndividualClient("id1","John","Doe"), List.of());
        // When
        double total = cartService.calculateTotal(cart);
        // Then
        assertEquals(0, total);
    }

    @Test
    void shouldCalculateTotalForSingleItem() {
        // Given
        CartItem item = new CartItem(ProductType.LAPTOP, 2);
        Cart cart = new Cart(new IndividualClient("id1","John","Doe"), List.of(item));
        given(pricingService.getUnitPrice(ProductType.LAPTOP, cart.client())).willReturn(1200.0);
        // When
        double total = cartService.calculateTotal(cart);
        // Then
        assertEquals(2400, total);
    }

    @Test
    void shouldCalculateTotalForMultipleItems() {
        // Given
        IndividualClient client = new IndividualClient("id1","John","Doe");
        CartItem i1 = new CartItem(ProductType.HIGH_END_PHONE, 1);
        CartItem i2 = new CartItem(ProductType.MID_RANGE_PHONE, 3);
        Cart cart = new Cart(client, List.of(i1, i2));
        given(pricingService.getUnitPrice(ProductType.HIGH_END_PHONE, client)).willReturn(1500.0);
        given(pricingService.getUnitPrice(ProductType.MID_RANGE_PHONE, client)).willReturn(800.0);
        // When
        double total = cartService.calculateTotal(cart);
        // Then
        assertEquals(1500 + (3 * 800), total);
    }
}
