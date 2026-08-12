package com.zeynep.eTicaretSitesi.logic.cart;
import com.zeynep.eTicaretSitesi.core.dao.BaseLogic;
import com.zeynep.eTicaretSitesi.core.entity.Cart;
import com.zeynep.eTicaretSitesi.dto.cart.CartResponse;
import com.zeynep.eTicaretSitesi.mapper.cart.CartMapper;
import com.zeynep.eTicaretSitesi.repo.cart.CartRepository;
import org.springframework.stereotype.Component;


@Component
public class CartLogic extends BaseLogic<Cart, Long, CartRepository> {

    private final CartMapper mapper;

    public CartLogic(CartRepository repository, CartMapper mapper) {
        super(repository);
        this.mapper=mapper;
    }
    public Cart createCart() {
        return new Cart();
    }
    public CartResponse toResponse(Cart cart) {
        return mapper.toResponse(cart);
    }

}
