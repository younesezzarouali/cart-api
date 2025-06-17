package com.younes.capco.cartexercise.api.cart;

import com.younes.capco.cartexercise.domain.cart.CartService;
import com.younes.capco.cartexercise.domain.cart.model.Cart;
import com.younes.capco.cartexercise.domain.cart.model.CartItem;
import com.younes.capco.cartexercise.domain.client.model.IndividualClient;
import com.younes.capco.cartexercise.domain.product.model.ProductType;
import com.younes.capco.cartexercise.domain.cart.port.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private CartService cartService;
    @MockBean private CartRepository cartRepository;

   /*TODO: To be fixed @Test
    void shouldCalculateAndSaveCart() throws Exception {
        // Given
        String req = "{\"client\":{\"id\":\"id1\",\"firstName\":\"John\",\"lastName\":\"Doe\"},\"items\":[{\"type\":\"LAPTOP\",\"quantity\":2}]}";
        given(cartService.calculateTotal(any(Cart.class))).willReturn(2400.0);
        // When / Then
        mockMvc.perform(post("/api/cart/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(req))
            .andExpect(status().isOk())
            .andExpect(content().string("2400.0"));
    }*/

    @Test
    void shouldGetCartWhenExists() throws Exception {
        // Given
        Cart cart = new Cart(new IndividualClient("id1","John","Doe"), List.of(new CartItem(ProductType.LAPTOP,2)));
        given(cartRepository.findById("id1")).willReturn(java.util.Optional.of(cart));
        // When / Then
        mockMvc.perform(get("/api/cart/id1"))
            .andExpect(status().isOk())
            .andExpect(content().json(jsonOf(cart)));
    }

    @Test
    void shouldReturn404WhenCartNotFound() throws Exception {
        // Given
        given(cartRepository.findById("unknown")).willReturn(java.util.Optional.empty());
        // When / Then
        mockMvc.perform(get("/api/cart/unknown"))
            .andExpect(status().isNotFound());
    }

    private String jsonOf(Object obj) {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

