package com.wfarooq.orderservice.entity;

import com.wfarooq.orderservice.enums.OrderItemStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Order order;


    @Column(nullable = false)
    private String productSku;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private String department;


    private Integer allocatedStock;

    @Enumerated(EnumType.STRING)
    private OrderItemStatus status;

}
