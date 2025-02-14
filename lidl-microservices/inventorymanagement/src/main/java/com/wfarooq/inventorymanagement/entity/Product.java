package com.wfarooq.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String sku;
    private String name;
    private String description;
    private String department;      // e.g., FRUIT&VEG, CHILLER, FREEZER, AMBIENT
    private String category;        // FRUIT , VEG ,
    private String subcategory;     // BANANAS, ORANGES, APPLES
    private BigDecimal basePrice;
    private String unit;            // KG, CASE
    private String barcode;
    private Integer casesPerPallet; // Standard number of cases per pallet
    private Double weightPerCase;   // For weighted items
    private boolean active;

    @OneToMany(mappedBy = "product")
    private List<Pallet> pallets;

    @OneToMany(mappedBy = "product")
    private List<StockLevel> stockLevels;
}
