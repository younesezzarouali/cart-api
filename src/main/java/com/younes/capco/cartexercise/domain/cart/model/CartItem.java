package com.younes.capco.cartexercise.domain.cart.model;

import com.younes.capco.cartexercise.domain.product.model.ProductType;

public record CartItem(ProductType type, int quantity) {}
