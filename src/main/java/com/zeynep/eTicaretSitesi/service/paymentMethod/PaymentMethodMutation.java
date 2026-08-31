package com.zeynep.eTicaretSitesi.service.paymentMethod;

import com.zeynep.eTicaretSitesi.dto.paymentMethod.PaymentMethodInput;
import com.zeynep.eTicaretSitesi.dto.paymentMethod.PaymentMethodResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentMethodMutation {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodMutation(
            PaymentMethodService paymentMethodService) {

        this.paymentMethodService = paymentMethodService;
    }

    @MutationMapping
    @PreAuthorize("hasRole('USER')")
    public PaymentMethodResponse createPaymentMethod(
            @Argument PaymentMethodInput input) {

        return paymentMethodService.create(input);
    }
}