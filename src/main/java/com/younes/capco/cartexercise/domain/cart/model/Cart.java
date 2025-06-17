package com.younes.capco.cartexercise.domain.cart.model;

import com.younes.capco.cartexercise.domain.client.model.Client;
import java.util.List;

public record Cart(Client client, List<CartItem> items) {}
