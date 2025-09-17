package com.younes.capco.cartexercise.domain.cart;

import com.younes.capco.cartexercise.domain.cart.model.Cart;
import com.younes.capco.cartexercise.domain.product.PricingService;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final PricingService pricingService;
    public CartService(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    public double calculateTotal(Cart cart) {
        return cart.items().stream()
            .mapToDouble(item -> item.quantity() * pricingService.getUnitPrice(item.type(), cart.client()))
            .sum();
    }
}