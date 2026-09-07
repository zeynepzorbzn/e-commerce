package com.zeynep.eTicaretSitesi.service.mail;

import java.math.BigDecimal;

public class OrderMailItem {

    private final String productName;
    private final String size;
    private final String color;
    private final Integer quantity;
    private final BigDecimal unitPrice;

    public OrderMailItem(
            String productName,
            String size,
            String color,
            Integer quantity,
            BigDecimal unitPrice
    ) {
        this.productName = productName;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}