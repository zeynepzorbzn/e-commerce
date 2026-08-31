package com.zeynep.eTicaretSitesi.repo.paymentMethod;

import com.zeynep.eTicaretSitesi.core.dao.BaseRepository;
import com.zeynep.eTicaretSitesi.core.entity.PaymentMethod;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepository
        extends BaseRepository<PaymentMethod, Long> {

    List<PaymentMethod> findByUserId(Long userId);

    Optional<PaymentMethod> findByIdAndUserId(Long id, Long userId);
}