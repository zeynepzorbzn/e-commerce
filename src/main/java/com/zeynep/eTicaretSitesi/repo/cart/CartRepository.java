package com.zeynep.eTicaretSitesi.repo.cart;

import com.zeynep.eTicaretSitesi.core.dao.BaseRepository;
import com.zeynep.eTicaretSitesi.core.entity.Cart;
import org.springframework.stereotype.Repository;

import java.util.Optional;

    @Repository
    public interface CartRepository extends BaseRepository<Cart, Long> {

        Optional<Cart> findByUserId(Long userId);
    }


