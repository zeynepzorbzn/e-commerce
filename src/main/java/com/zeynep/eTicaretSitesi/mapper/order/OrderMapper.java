package com.zeynep.eTicaretSitesi.mapper.order;

import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.Order;
import com.zeynep.eTicaretSitesi.dto.order.OrderInput;
import com.zeynep.eTicaretSitesi.dto.order.OrderResponse;
import com.zeynep.eTicaretSitesi.dto.orderItem.OrderItemResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper extends BaseMapper<Order, OrderInput, OrderResponse> {

    @Override
    public Order toEntity(OrderInput input) {
        return new Order();
    }
    @Override
    public OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setCode(order.getCode());
        response.setTotalPrice(order.getTotalPrice());
        response.setProductCount(order.getProductCount());

    if (order.getUser() != null) {
       response.setUserId(order.getUser().getId());}
    if (order.getOrderItems() != null) {
        response.setOrderItems(order.getOrderItems().stream().map(item -> {OrderItemResponse itemResponse = new OrderItemResponse();
            itemResponse.setId(item.getId());
            itemResponse.setQuantity(item.getQuantity());
            itemResponse.setUnitPrice(item.getUnitPrice());

    if (item.getProductVariant() != null) {
        itemResponse.setProductVariantId(item.getProductVariant().getId());
        itemResponse.setSize(item.getProductVariant().getSize());
        itemResponse.setColor(item.getProductVariant().getColor());

    if (item.getProductVariant().getProduct() != null) {
        itemResponse.setProductName(item.getProductVariant().getProduct().getName());}}
           return itemResponse;}).toList());
        } else {
            response.setOrderItems(List.of());
        }
        return response;
    }
    @Override
    public void updateEntity(Order order, OrderInput input) {
        // Order update edilmeyecek.
    }
    @Override
    public List<OrderResponse> toResponseList(List<Order> order) {
        return order.stream().map(this::toResponse).toList();
    }
}