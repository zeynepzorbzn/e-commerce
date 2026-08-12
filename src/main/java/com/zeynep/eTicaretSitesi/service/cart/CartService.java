package com.zeynep.eTicaretSitesi.service.cart;

import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import com.zeynep.eTicaretSitesi.core.entity.Cart;
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.dto.cart.CartInput;
import com.zeynep.eTicaretSitesi.dto.cart.CartResponse;
import com.zeynep.eTicaretSitesi.logic.cart.CartLogic;
import com.zeynep.eTicaretSitesi.logic.user.UserLogic;
import com.zeynep.eTicaretSitesi.mapper.cart.CartMapper;
import com.zeynep.eTicaretSitesi.repo.cart.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService extends BaseService<Cart, CartInput, Long, CartLogic, CartMapper, CartRepository, CartResponse> {

    private final UserLogic userLogic;

    public CartService (CartRepository repository, CartLogic logic, CartMapper mapper, UserLogic userLogic){
        super(repository, logic, mapper);
        this.userLogic=userLogic;
    }
    @Override
    public CartResponse create(CartInput input){
        Cart cart = logic.createCart();
        User user =  userLogic.findById(input.getUserId()).orElseThrow(()-> new RuntimeException("Kullanıcı Bulunamadı."));
        cart.setUser(user);
        Cart savedCart = repository.save(cart);
        return logic.toResponse(savedCart);
    }
    public CartResponse getByUserId(Long userId) {
        Cart cart = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Sepet bulunamadı."));
        return logic.toResponse(cart);
    }

}
