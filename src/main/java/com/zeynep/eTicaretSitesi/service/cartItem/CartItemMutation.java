package com.zeynep.eTicaretSitesi.service.cartItem;

import com.zeynep.eTicaretSitesi.dto.cartItem.CartItemInput;
import com.zeynep.eTicaretSitesi.dto.cartItem.CartItemResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class CartItemMutation {

    private final CartItemService cartItemService;
    public CartItemMutation(CartItemService cartItemService) {
        this.cartItemService = cartItemService;}

    @MutationMapping
    @PreAuthorize("hasRole('USER')")
    public CartItemResponse addToCart(
            @Argument CartItemInput input
    ) {
        return cartItemService.addToCart(input);
    }

    @MutationMapping
    @PreAuthorize("hasRole('USER')")
    public CartItemResponse updateCartItemQuantity(
            @Argument Long cartItemId,
            @Argument Integer quantity
    ) {
        return cartItemService.updateQuantity(cartItemId, quantity);
    }

    @MutationMapping
    @PreAuthorize("hasRole('USER')")
    public Boolean removeFromCart(
            @Argument Long cartItemId
    ) {
        return cartItemService.removeFromCart(cartItemId);
    }
}