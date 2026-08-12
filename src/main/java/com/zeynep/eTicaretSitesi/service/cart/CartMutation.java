package com.zeynep.eTicaretSitesi.service.cart;

import com.zeynep.eTicaretSitesi.dto.cart.CartInput;
import com.zeynep.eTicaretSitesi.dto.cart.CartResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class CartMutation {

    private final CartService cartService;

    public CartMutation(CartService cartService) {
        this.cartService = cartService;
    }

    @MutationMapping
    @PreAuthorize("hasRole('USER')")
    public CartResponse createCart(@Argument CartInput input) {
        return cartService.create(input);
    }
}