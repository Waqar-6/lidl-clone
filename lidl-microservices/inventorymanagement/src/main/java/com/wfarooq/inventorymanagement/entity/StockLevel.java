package com.wfarooq.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "stock_levels")
public class StockLevel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    
    private Integer totalPallets;
    private Integer totalCases;
    private Integer reservedCases;  // Cases reserved for picking
    private Integer minimumCases;
    private Integer reorderPoint;
    
    @Version
    private Long version;           // For optimistic locking
}