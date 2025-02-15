package com.wfarooq.inventorymanagement.dto.response;

import com.wfarooq.inventorymanagement.enums.ZoneType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor
public class BinLocationResponse {
    private String aisleNumber;
    private String binNumber;
    private String fullLocation;
    private Integer maxPallets;
    private ZoneType zone;
    private Integer currentPallets;
    
    // Audit fields
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}