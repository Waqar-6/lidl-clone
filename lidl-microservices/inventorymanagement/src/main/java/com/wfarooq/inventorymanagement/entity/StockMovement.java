package com.wfarooq.inventorymanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "stock_movements")
public class StockMovement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "pallet_id")
    private Pallet pallet;
    
    @ManyToOne
    @JoinColumn(name = "source_location_id")
    private BinLocation sourceLocation;
    
    @ManyToOne
    @JoinColumn(name = "destination_location_id")
    private BinLocation destinationLocation;
    
    private Integer casesQuantity;
    private String movementType;    // RECEIVE, PICK, TRANSFER, ADJUST
    private String reference;       // Order reference or adjustment reference
    private String pickerReference; // For voice picking system
    private LocalDateTime timestamp;
    private String status;          // PENDING, COMPLETED, CANCELLED
}