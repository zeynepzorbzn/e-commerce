package com.zeynep.eTicaretSitesi.service.order;

import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import com.zeynep.eTicaretSitesi.core.entity.*;
import com.zeynep.eTicaretSitesi.dto.order.OrderInput;
import com.zeynep.eTicaretSitesi.dto.order.OrderResponse;
import com.zeynep.eTicaretSitesi.logic.order.OrderLogic;
import com.zeynep.eTicaretSitesi.mapper.order.OrderMapper;
import com.zeynep.eTicaretSitesi.repo.order.OrderRepository;
import com.zeynep.eTicaretSitesi.repo.orderItem.OrderItemRepository;
import com.zeynep.eTicaretSitesi.repo.cartItem.CartItemRepository;
import com.zeynep.eTicaretSitesi.repo.cart.CartRepository;
import com.zeynep.eTicaretSitesi.repo.productVariant.ProductVariantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService extends BaseService<Order, OrderInput, Long, OrderLogic, OrderMapper, OrderRepository, OrderResponse> {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductVariantRepository productVariantRepository;

    public OrderService(OrderRepository repository, OrderLogic logic, OrderMapper mapper, CartRepository cartRepository,
           CartItemRepository cartItemRepository, OrderItemRepository orderItemRepository, ProductVariantRepository productVariantRepository) {

        super(repository, logic, mapper);
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderItemRepository = orderItemRepository;
        this.productVariantRepository = productVariantRepository;
    }

    @Transactional
    @Override
    public OrderResponse create(OrderInput input) {
        Cart cart = cartRepository.findById(input.getCartId()).orElseThrow(() -> new RuntimeException("Sepet bulunamadı."));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Sepet boş.");
        }
        Order order = new Order();
        order.setCode("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        order.setUser(cart.getUser());
        order.setTotalPrice(cart.getTotalPrice());
        order.setProductCount(cart.getProductCount());

        Order savedOrder = repository.save(order);
        BigDecimal totalPrice = BigDecimal.ZERO;
        int productCount = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {

          ProductVariant variant = cartItem.getProductVariant();
            if (variant.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Yeterli stok yok: " + variant.getProduct().getName());
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProductVariant(variant);
            orderItem.setQuantity(cartItem.getQuantity());

            BigDecimal unitPrice = variant.getProduct().getPrice();
            orderItem.setUnitPrice(unitPrice);
            OrderItem savedOrderItem = orderItemRepository.save(orderItem);

            orderItems.add(savedOrderItem);
            variant.setStock(variant.getStock() - cartItem.getQuantity());
            productVariantRepository.save(variant);
            BigDecimal itemTotal = unitPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            totalPrice = totalPrice.add(itemTotal);
            productCount += cartItem.getQuantity();
        }

        savedOrder.setTotalPrice(totalPrice);
        savedOrder.setProductCount(productCount);
        savedOrder.setOrderItems(orderItems);
        Order finalOrder = repository.save(savedOrder);

        // Cart'ı temizle
        cartItemRepository.deleteAll(cart.getItems());
        cart.setTotalPrice(BigDecimal.ZERO);
        cart.setProductCount(0);
        cartRepository.save(cart);

        return logic.toResponse(finalOrder);
    }
}