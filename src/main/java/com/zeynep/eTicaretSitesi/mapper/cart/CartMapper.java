package com.zeynep.eTicaretSitesi.mapper.cart;

import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.Cart;
import com.zeynep.eTicaretSitesi.dto.cart.CartInput;
import com.zeynep.eTicaretSitesi.dto.cart.CartResponse;
import com.zeynep.eTicaretSitesi.dto.cartItem.CartItemResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper extends BaseMapper<Cart, CartInput, CartResponse> {

    @Override
    public Cart toEntity(CartInput input) {return new Cart();}

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
            response.setItems(cart.getItems().stream().map(item -> {CartItemResponse itemResponse = new CartItemResponse();
                itemResponse.setId(item.getId());
                itemResponse.setQuantity(item.getQuantity());
                itemResponse.setTotalPrice(item.getTotalPrice());

        if (item.getProductVariant() != null) {
            itemResponse.setProductVariantId(item.getProductVariant().getId());
            itemResponse.setSize(item.getProductVariant().getSize());
            itemResponse.setColor(item.getProductVariant().getColor());

        if (item.getProductVariant().getProduct() != null) {
            itemResponse.setProductName(item.getProductVariant().getProduct().getName());}
        }
        return itemResponse;}).toList());
        }
        return response;
    }
    @Override
    public void updateEntity(Cart cart, CartInput input) {
    }
    @Override
    public List<CartResponse> toResponseList(List<Cart> cart) {
        return cart.stream().map(this::toResponse).toList();
    }
}