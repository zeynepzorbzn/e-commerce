package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "payment_methods")
public class PaymentMethod extends BaseEntity {

    private String maskedCardNumber;
    private String cardHolder;
    private String provider;
    private String lastFourDigits;
    private Integer expireMonth;
    private Integer expireYear;
    private boolean isDefault = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public PaymentMethod() {
    }
    public String getMaskedCardNumber() {return maskedCardNumber;}
    public void setMaskedCardNumber(String maskedCardNumber) {this.maskedCardNumber = maskedCardNumber;}

    public String getCardHolder() {return cardHolder;}
    public void setCardHolder(String cardHolder) {this.cardHolder = cardHolder;}

    public String getProvider() {return provider;}
    public void setProvider(String provider) {this.provider = provider;}

    public String getLastFourDigits() {return lastFourDigits;}
    public void setLastFourDigits(String lastFourDigits) {this.lastFourDigits = lastFourDigits;}

    public Integer getExpireMonth() {return expireMonth;}
    public void setExpireMonth(Integer expireMonth) {this.expireMonth = expireMonth;}

    public Integer getExpireYear() {return expireYear;}
    public void setExpireYear(Integer expireYear) {this.expireYear = expireYear;}

    public boolean getDefault() {return isDefault;}
    public void setDefault(boolean isDefault) {this.isDefault = isDefault;}

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
}