package com.wfarooq.inventorymanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockLevelResponse {
    private String productSku;
    private String productName;
    private Integer totalPallets;
    private Integer totalCases;
    private Integer reservedCases;
    private Integer minimumCases;
    private Integer reorderPoint;
    private Boolean needsReorder;  // Calculated field
    
    // Audit fields
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}