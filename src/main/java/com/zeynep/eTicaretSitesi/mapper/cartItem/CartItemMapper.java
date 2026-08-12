package com.zeynep.eTicaretSitesi.mapper.cartItem;

import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.CartItem;
import com.zeynep.eTicaretSitesi.dto.cartItem.CartItemInput;
import com.zeynep.eTicaretSitesi.dto.cartItem.CartItemResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartItemMapper extends BaseMapper<CartItem, CartItemInput, CartItemResponse> {

    @Override
    public CartItem toEntity(CartItemInput input) {
        CartItem item = new CartItem();
        item.setQuantity(input.getQuantity());
        return item;
    }
    @Override
    public CartItemResponse toResponse(CartItem item) {

        CartItemResponse response = new CartItemResponse();
        response.setId(item.getId());
        response.setQuantity(item.getQuantity());
        response.setTotalPrice(item.getTotalPrice());

        if (item.getProductVariant() != null) {
            response.setProductVariantId(item.getProductVariant().getId());
            response.setSize(item.getProductVariant().getSize());
            response.setColor(item.getProductVariant().getColor());
            if (item.getProductVariant().getProduct() != null) {
                response.setProductName(item.getProductVariant().getProduct().getName());}
        } return response;
    }
    @Override
    public void updateEntity(CartItem item, CartItemInput input) {
        item.setQuantity(input.getQuantity());
    }
    @Override
    public List<CartItemResponse> toResponseList(List<CartItem> items) {
        return items.stream().map(this::toResponse).toList();
    }
}