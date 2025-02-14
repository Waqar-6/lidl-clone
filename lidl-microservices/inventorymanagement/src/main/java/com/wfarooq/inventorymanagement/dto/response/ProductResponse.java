package com.wfarooq.inventorymanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String sku;
    private String name;
    private String description;
    private String department;
    private String category;
    private String subcategory;
    private BigDecimal basePrice;
    private String unit;
    private String barcode;
    private Integer casesPerPallet;
    private Double weightPerCase;
    private boolean active;
    

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    

    private Integer totalPallets;
    private Integer totalCases;
}