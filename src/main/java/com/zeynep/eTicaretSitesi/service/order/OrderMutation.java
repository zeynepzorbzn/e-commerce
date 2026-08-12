package com.zeynep.eTicaretSitesi.service.order;

import com.zeynep.eTicaretSitesi.dto.order.OrderInput;
import com.zeynep.eTicaretSitesi.dto.order.OrderResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class OrderMutation {

    private final OrderService orderService;

    public OrderMutation(OrderService orderService) {
        this.orderService = orderService;
    }

    @MutationMapping
    @PreAuthorize("hasRole('USER')")
    public OrderResponse createOrder(
            @Argument OrderInput input) {

        return orderService.create(input);
    }
}