package com.wfarooq.orderservice.entity;

import jakarta.persistence.*;

import java.util.UUID;

public class OrderItem extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private int quantityOrdered;

    @Column(nullable = false)
    private int allocatedStock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderItemStatus status;
}
