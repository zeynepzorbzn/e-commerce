package com.zeynep.eTicaretSitesi.service.mail;

import java.util.List;

public class OrderCreatedEvent {

    private final String email;
    private final String customerName;
    private final String orderCode;
    private final String orderDate;
    private final String totalPrice;

    private final String addressName;
    private final String city;
    private final String district;
    private final String street;
    private final String postalCode;

    private final List<OrderMailItem> items;

    public OrderCreatedEvent(
            String email,
            String customerName,
            String orderCode,
            String orderDate,
            String totalPrice,
            String addressName,
            String city,
            String district,
            String street,
            String postalCode,
            List<OrderMailItem> items
    ) {
        this.email = email;
        this.customerName = customerName;
        this.orderCode = orderCode;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.addressName = addressName;
        this.city = city;
        this.district = district;
        this.street = street;
        this.postalCode = postalCode;
        this.items = items;
    }

    public String getEmail() {
        return email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getAddressName() {
        return addressName;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public List<OrderMailItem> getItems() {
        return items;
    }
}