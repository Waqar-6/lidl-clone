package com.wfarooq.inventorymanagement.entity;

import com.wfarooq.inventorymanagement.enums.PalletStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter @Setter @ToString  @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "pallets")
public class Pallet extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String huNumber;        // Handling Unit number (e.g., "786")
    private Integer totalCases;     // Initial cases on pallet
    private Integer remainingCases; // Current cases left
    private LocalDateTime receivedAt;
    private LocalDateTime expiryDate;
    
    @Enumerated(EnumType.STRING)
    private PalletStatus status;    // RECEIVED, IN_STORAGE, PICKING, EMPTY
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "stock_level_id")
    private StockLevel stockLevel;
    
    @ManyToOne
    @JoinColumn(name = "bin_location_id")
    private BinLocation currentLocation;
    
    @OneToMany(mappedBy = "pallet")
    private List<StockMovement> movements;
}