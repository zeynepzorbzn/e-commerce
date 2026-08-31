package com.zeynep.eTicaretSitesi.mapper.cart;

import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.Cart;
import com.zeynep.eTicaretSitesi.dto.cart.CartInput;
import com.zeynep.eTicaretSitesi.dto.cart.CartResponse;
import com.zeynep.eTicaretSitesi.mapper.cartItem.CartItemMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper extends BaseMapper<Cart, CartInput, CartResponse> {

    private final CartItemMapper cartItemMapper;

    public CartMapper(CartItemMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public Cart toEntity(CartInput input) {
        return new Cart();
    }

    @Override
    public CartResponse toResponse(Cart cart) {

        CartResponse response = new CartResponse();

        response.setId(cart.getId());
        response.setTotalPrice(cart.getTotalPrice());
        response.setProductCount(cart.getProductCount());

        if (cart.getUser() != null) {
            response.setUserId(cart.getUser().getId());
        }

        if (cart.getItems() != null) {
            response.setItems(
                    cartItemMapper.toResponseList(cart.getItems())
            );
        }

        return response;
    }

    @Override
    public void updateEntity(Cart cart, CartInput input) {
    }

    @Override
    public List<CartResponse> toResponseList(List<Cart> carts) {
        return carts.stream()
                .map(this::toResponse)
                .toList();
    }
}