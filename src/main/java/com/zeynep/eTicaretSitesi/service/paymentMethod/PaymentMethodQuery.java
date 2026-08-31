package com.zeynep.eTicaretSitesi.service.paymentMethod;

import com.zeynep.eTicaretSitesi.dto.paymentMethod.PaymentMethodResponse;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PaymentMethodQuery {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodQuery(
            PaymentMethodService paymentMethodService) {

        this.paymentMethodService = paymentMethodService;
    }

    @QueryMapping
    @PreAuthorize("hasRole('USER')")
    public List<PaymentMethodResponse> getMyPaymentMethods() {

        return paymentMethodService.getMyPaymentMethods();
    }
}