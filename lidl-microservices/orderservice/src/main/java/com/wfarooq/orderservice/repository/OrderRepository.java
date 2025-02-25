package com.wfarooq.orderservice.repository;

import com.wfarooq.orderservice.entity.Order;
import com.wfarooq.orderservice.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findByOrderNumber(String orderNumber);
    List<Order> findByStoreNumber(String storeNumber);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<Order> findByRequestedDeliveryTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Order> findByStoreNumberAndStatus(String storeNumber, OrderStatus status);
    boolean existsByOrderNumber(String orderNumber);
}
