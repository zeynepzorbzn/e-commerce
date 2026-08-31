package com.zeynep.eTicaretSitesi.service.cart;

import com.zeynep.eTicaretSitesi.dto.cart.CartResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class CartQuery {

    private final CartService cartService;

    public CartQuery(CartService cartService) {
        this.cartService = cartService;
    }

    @QueryMapping
    @PreAuthorize("hasRole('USER')")
    public CartResponse getMyCart() {
        return cartService.getByUser();
    }
}