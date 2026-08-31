package com.zeynep.eTicaretSitesi.mapper.paymentMethod;

import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.PaymentMethod;
import com.zeynep.eTicaretSitesi.dto.paymentMethod.PaymentMethodInput;
import com.zeynep.eTicaretSitesi.dto.paymentMethod.PaymentMethodResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMethodMapper
        extends BaseMapper<
        PaymentMethod,
        PaymentMethodInput,
        PaymentMethodResponse> {

    @Override
    public PaymentMethod toEntity(PaymentMethodInput input) {

        PaymentMethod paymentMethod = new PaymentMethod();

        String cardNumber =
                input.getCardNumber().replaceAll("\\s+", "");

        String lastFourDigits =
                cardNumber.substring(cardNumber.length() - 4);

        String maskedCardNumber =
                "**** **** **** " + lastFourDigits;

        paymentMethod.setMaskedCardNumber(maskedCardNumber);
        paymentMethod.setLastFourDigits(lastFourDigits);
        paymentMethod.setCardHolder(input.getCardHolder());
        paymentMethod.setExpireMonth(input.getExpireMonth());
        paymentMethod.setExpireYear(input.getExpireYear());
        paymentMethod.setDefault(input.isDefault());

        /*
         * Gerçek ödeme entegrasyonu olmadığı için
         * kart sağlayıcısını basitçe belirliyoruz.
         */
        paymentMethod.setProvider(
                detectProvider(cardNumber)
        );

        return paymentMethod;
    }

    @Override
    public PaymentMethodResponse toResponse(
            PaymentMethod paymentMethod) {

        PaymentMethodResponse response =
                new PaymentMethodResponse();

        response.setId(paymentMethod.getId());
        response.setMaskedCardNumber(
                paymentMethod.getMaskedCardNumber()
        );
        response.setCardHolder(
                paymentMethod.getCardHolder()
        );
        response.setProvider(
                paymentMethod.getProvider()
        );
        response.setLastFourDigits(
                paymentMethod.getLastFourDigits()
        );
        response.setExpireMonth(
                paymentMethod.getExpireMonth()
        );
        response.setExpireYear(
                paymentMethod.getExpireYear()
        );
        response.setDefault(
                paymentMethod.getDefault()
        );

        return response;
    }

    @Override
    public void updateEntity(
            PaymentMethod paymentMethod,
            PaymentMethodInput input) {

        String cardNumber =
                input.getCardNumber().replaceAll("\\s+", "");

        String lastFourDigits =
                cardNumber.substring(cardNumber.length() - 4);

        paymentMethod.setMaskedCardNumber(
                "**** **** **** " + lastFourDigits
        );

        paymentMethod.setLastFourDigits(lastFourDigits);
        paymentMethod.setCardHolder(input.getCardHolder());
        paymentMethod.setExpireMonth(input.getExpireMonth());
        paymentMethod.setExpireYear(input.getExpireYear());
        paymentMethod.setDefault(input.isDefault());
        paymentMethod.setProvider(
                detectProvider(cardNumber)
        );
    }

    @Override
    public List<PaymentMethodResponse> toResponseList(
            List<PaymentMethod> paymentMethods) {

        return paymentMethods.stream()
                .map(this::toResponse)
                .toList();
    }

    private String detectProvider(String cardNumber) {

        if (cardNumber.startsWith("4")) {
            return "VISA";
        }

        if (cardNumber.startsWith("5")) {
            return "MASTERCARD";
        }

        return "CARD";
    }
}