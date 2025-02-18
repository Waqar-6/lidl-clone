package com.wfarooq.inventorymanagement.dto.response;

import com.wfarooq.inventorymanagement.enums.MovementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMovementResponse {
    private String productSku;
    private String productName;
    private String palletHuNumber;
    private String sourceLocation;
    private String destinationLocation;
    private Integer casesQuantity;
    private MovementType movementType;
    private String reference;
    private String pickerReference;
    private LocalDateTime timestamp;
    private String status;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}