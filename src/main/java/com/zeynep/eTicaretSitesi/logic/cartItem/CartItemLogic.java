package com.zeynep.eTicaretSitesi.logic.cartItem;

import com.zeynep.eTicaretSitesi.core.dao.BaseLogic;
import com.zeynep.eTicaretSitesi.core.entity.CartItem;
import com.zeynep.eTicaretSitesi.dto.cartItem.CartItemResponse;
import com.zeynep.eTicaretSitesi.mapper.cartItem.CartItemMapper;
import com.zeynep.eTicaretSitesi.repo.cartItem.CartItemRepository;
import org.springframework.stereotype.Component;

@Component
public class CartItemLogic extends BaseLogic<CartItem, Long, CartItemRepository> {

    private final CartItemMapper mapper;

    public CartItemLogic(CartItemRepository repository, CartItemMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }
    public CartItem createCartItem(Integer quantity) {
        CartItem item = new CartItem();
        item.setQuantity(quantity);
        return item;
    }
    public CartItemResponse toResponse(CartItem item) {
        return mapper.toResponse(item);
    }
}