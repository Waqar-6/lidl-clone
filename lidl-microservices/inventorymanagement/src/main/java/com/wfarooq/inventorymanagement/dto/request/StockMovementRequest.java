package com.wfarooq.inventorymanagement.dto.request;

import com.wfarooq.inventorymanagement.enums.MovementType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMovementRequest {
    @NotBlank(message = "Product SKU is required")
    private String productSku;

    @NotBlank(message = "Pallet HU number is required")
    private String palletHuNumber;

    @NotBlank(message = "Source location is required")
    private String sourceLocation;


    private String destinationLocation;

    private Integer casesQuantity;

    @NotBlank(message = "Movement type is required")
    private MovementType movementType;

    private String reference;
    private String pickerReference;
    private String status = "PENDING";
}