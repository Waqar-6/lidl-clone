package com.wfarooq.inventorymanagement.dto.request;

import com.wfarooq.inventorymanagement.enums.ZoneType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class BinLocationRequest {
    @NotBlank(message = "Aisle number is required")
    private String aisleNumber;
    
    @NotBlank(message = "Bin number is required")
    private String binNumber;
    
    @NotNull(message = "Maximum pallets capacity is required")
    @Min(value = 1, message = "Maximum pallets must be at least 1")
    private Integer maxPallets;
    
    @NotNull(message = "Zone type is required")
    private ZoneType zone;
}