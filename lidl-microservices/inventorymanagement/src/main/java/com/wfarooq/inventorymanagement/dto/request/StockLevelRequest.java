package com.wfarooq.inventorymanagement.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockLevelRequest {
    @NotBlank(message = "Product SKU is required")
    private String productSku;

    @NotNull(message = "Total pallets are required")
    @Min(value = 0, message = "Total pallets cannot be negative")
    private Integer totalPallets;

    @NotNull(message = "Total cases are required")
    @Min(value = 0, message = "Total cases cannot be negative")
    private Integer totalCases;

    @NotNull(message = "Reserved cases are required")
    @Min(value = 0, message = "Reserved cases cannot be negative")
    private Integer reservedCases;

    @NotNull(message = "Minimum cases are required")
    @Min(value = 0, message = "Minimum cases cannot be negative")
    private Integer minimumCases;

    @NotNull(message = "Reorder point is required")
    @Min(value = 1, message = "Reorder point must be positive")
    private Integer reorderPoint;
}