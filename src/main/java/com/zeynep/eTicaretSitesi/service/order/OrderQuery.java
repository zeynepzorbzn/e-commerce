package com.zeynep.eTicaretSitesi.service.order;

import com.zeynep.eTicaretSitesi.dto.order.OrderResponse;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderQuery {

    private final OrderService orderService;

    public OrderQuery(OrderService orderService) {
        this.orderService = orderService;
    }

    @QueryMapping
    @PreAuthorize("hasRole('USER')")
    public List<OrderResponse> getMyOrders() {

        return orderService.getMyOrders();
    }
}