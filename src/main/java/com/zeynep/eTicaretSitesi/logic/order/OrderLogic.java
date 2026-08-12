package com.zeynep.eTicaretSitesi.logic.order;

import com.zeynep.eTicaretSitesi.core.dao.BaseLogic;
import com.zeynep.eTicaretSitesi.core.entity.Order;
import com.zeynep.eTicaretSitesi.dto.order.OrderResponse;
import com.zeynep.eTicaretSitesi.mapper.order.OrderMapper;
import com.zeynep.eTicaretSitesi.repo.order.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderLogic extends BaseLogic<Order, Long, OrderRepository> {

    private final OrderMapper mapper;

    public OrderLogic(OrderRepository repository, OrderMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    public OrderResponse toResponse(Order order) {
        return mapper.toResponse(order);
    }
}