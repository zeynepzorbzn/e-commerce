package com.zeynep.eTicaretSitesi.dto.paymentMethod;

import com.zeynep.eTicaretSitesi.core.dao.BaseInput;
import com.zeynep.eTicaretSitesi.core.entity.PaymentMethod;

public class PaymentMethodInput extends BaseInput<PaymentMethod> {

    private String cardNumber;
    private String cardHolder;
    private Integer expireMonth;
    private Integer expireYear;
    private boolean isDefault;

    public PaymentMethodInput() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Integer getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(Integer expireMonth) {
        this.expireMonth = expireMonth;
    }

    public Integer getExpireYear() {
        return expireYear;
    }

    public void setExpireYear(Integer expireYear) {
        this.expireYear = expireYear;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}