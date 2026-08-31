package com.zeynep.eTicaretSitesi.repo.order;

import com.zeynep.eTicaretSitesi.core.dao.BaseRepository;
import com.zeynep.eTicaretSitesi.core.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {

    @Query("""
        SELECT DISTINCT o
        FROM Order o
        LEFT JOIN FETCH o.orderItems
        WHERE o.id = :id
        """)
    Optional<Order> findByIdWithOrderItems(@Param("id") Long id);

    @Query("""
    SELECT DISTINCT o
    FROM Order o
    LEFT JOIN FETCH o.orderItems oi
    LEFT JOIN FETCH oi.productVariant pv
    LEFT JOIN FETCH pv.product p
    LEFT JOIN FETCH o.address
    LEFT JOIN FETCH o.paymentMethod
    WHERE o.user.id = :userId
    ORDER BY o.createdAt DESC
    """)
    List<Order> findMyOrders(@Param("userId") Long userId);
}