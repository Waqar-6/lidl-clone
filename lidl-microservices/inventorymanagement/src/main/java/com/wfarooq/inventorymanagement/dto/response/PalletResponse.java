package com.wfarooq.inventorymanagement.dto.response;

import com.wfarooq.inventorymanagement.enums.PalletStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PalletResponse {
    private String huNumber;
    private String productSku;
    private String productName;
    private Integer totalCases;
    private Integer remainingCases;
    private String currentLocation;
    private PalletStatus status;
    
    // Audit fields
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}