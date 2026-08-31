package com.zeynep.eTicaretSitesi.service.cart;

import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import com.zeynep.eTicaretSitesi.core.entity.Cart;
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.dto.cart.CartInput;
import com.zeynep.eTicaretSitesi.dto.cart.CartResponse;
import com.zeynep.eTicaretSitesi.logic.cart.CartLogic;
import com.zeynep.eTicaretSitesi.mapper.cart.CartMapper;
import com.zeynep.eTicaretSitesi.repo.cart.CartRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartService extends BaseService<
        Cart,
        CartInput,
        Long,
        CartLogic,
        CartMapper,
        CartRepository,
        CartResponse> {

    public CartService(CartRepository repository, CartLogic logic, CartMapper mapper) {
        super(repository, logic, mapper);
    }

    @Override
    public CartResponse create(CartInput input) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Cart cart = logic.createCart();
        cart.setUser(user);

        Cart savedCart = repository.save(cart);

        return logic.toResponse(savedCart);
    }

    public CartResponse getByUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Cart cart = repository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = logic.createCart();
                    newCart.setUser(user);
                    return repository.save(newCart);
                });

        return logic.toResponse(cart);
    }
}
