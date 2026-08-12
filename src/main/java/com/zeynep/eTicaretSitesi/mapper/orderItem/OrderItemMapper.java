package com.zeynep.eTicaretSitesi.mapper.orderItem;

import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.OrderItem;
import com.zeynep.eTicaretSitesi.dto.orderItem.OrderItemInput;
import com.zeynep.eTicaretSitesi.dto.orderItem.OrderItemResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemMapper extends BaseMapper<OrderItem, OrderItemInput, OrderItemResponse> {

    @Override
    public OrderItem toEntity(OrderItemInput input) {

        OrderItem item = new OrderItem();
        item.setQuantity(input.getQuantity());
        return item;
    }
    @Override
    public OrderItemResponse toResponse(OrderItem item) {

        OrderItemResponse response = new OrderItemResponse();

        response.setId(item.getId());
        response.setQuantity(item.getQuantity());
        response.setUnitPrice(item.getUnitPrice());

    if (item.getProductVariant() != null) {
         response.setProductVariantId(item.getProductVariant().getId());
         response.setSize(item.getProductVariant().getSize());
         response.setColor(item.getProductVariant().getColor());

    if (item.getProductVariant().getProduct() != null) {
         response.setProductName(item.getProductVariant().getProduct().getName());}
        }
        return response;
    }
    @Override
    public void updateEntity(OrderItem item, OrderItemInput input) {
        item.setQuantity(input.getQuantity());
    }
    @Override
    public List<OrderItemResponse> toResponseList(List<OrderItem> orderItem) {
        return orderItem.stream().map(this::toResponse).toList();
    }
}