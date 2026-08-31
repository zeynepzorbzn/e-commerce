package com.zeynep.eTicaretSitesi.logic.paymentMethod;

import com.zeynep.eTicaretSitesi.core.dao.BaseLogic;
import com.zeynep.eTicaretSitesi.core.entity.PaymentMethod;
import com.zeynep.eTicaretSitesi.dto.paymentMethod.PaymentMethodResponse;
import com.zeynep.eTicaretSitesi.repo.paymentMethod.PaymentMethodRepository;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodLogic extends BaseLogic<PaymentMethod, Long, PaymentMethodRepository> {

    public PaymentMethodLogic(PaymentMethodRepository repository) {

        super(repository);
    }
}