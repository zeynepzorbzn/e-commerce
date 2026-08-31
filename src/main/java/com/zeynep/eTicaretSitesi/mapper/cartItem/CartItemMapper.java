package com.zeynep.eTicaretSitesi.mapper.cartItem;

import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.CartItem;
import com.zeynep.eTicaretSitesi.core.entity.Product;
import com.zeynep.eTicaretSitesi.core.entity.ProductVariant;
import com.zeynep.eTicaretSitesi.dto.cartItem.CartItemInput;
import com.zeynep.eTicaretSitesi.dto.cartItem.CartItemResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartItemMapper
        extends BaseMapper<CartItem, CartItemInput, CartItemResponse> {

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

            ProductVariant variant = item.getProductVariant();

            response.setProductVariantId(variant.getId());
            response.setSize(variant.getSize());
            response.setColor(variant.getColor());

            if (variant.getProduct() != null) {

                response.setProductId(variant.getProduct().getId());
                response.setProductName(variant.getProduct().getName());
            }
            if (variant.getProduct() != null) {

                Product product = variant.getProduct();

                response.setProductId(product.getId());
                response.setProductName(product.getName());

                if (product.getProductImages() != null
                        && !product.getProductImages().isEmpty()) {

                    response.setImageToken(
                            product.getProductImages()
                                    .get(0)
                                    .getImageToken()
                    );
                }
            }
        }

        return response;
    }

    @Override
    public void updateEntity(CartItem item, CartItemInput input) {
        item.setQuantity(input.getQuantity());
    }

    @Override
    public List<CartItemResponse> toResponseList(
            List<CartItem> items
    ) {
        return items.stream()
                .map(this::toResponse)
                .toList();
    }
}