package com.younes.capco.cartexercise.api.cart;

import com.younes.capco.cartexercise.domain.cart.CartService;
import com.younes.capco.cartexercise.domain.cart.model.Cart;
import com.younes.capco.cartexercise.domain.cart.port.CartRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final CartRepository cartRepository;

    public CartController(CartService cartService, CartRepository cartRepository) {
        this.cartService = cartService;
        this.cartRepository = cartRepository;
    }

    @PostMapping("/calculate")
    public double calculateCart(@RequestBody Cart cart) {
        double total = cartService.calculateTotal(cart);
        cartRepository.save(cart);
        return total;
    }

    @GetMapping("/{clientId}")
    public Cart getCart(@PathVariable String clientId) {
        System.out.println("==> Getting super cart for client: " + clientId);
        return cartRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
