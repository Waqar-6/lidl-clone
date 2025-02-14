package com.wfarooq.inventorymanagement.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProductRequest {
    @NotBlank(message = "SKU is required")
    private String sku;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    private String description;
    
    @NotBlank(message = "Department is required")
    private String department;
    
    @NotBlank(message = "Category is required")
    private String category;
    
    private String subcategory;
    
    @NotNull(message = "Base price is required")
    @DecimalMin(value = "0.0", message = "Price must be positive")
    private BigDecimal basePrice;
    
    @NotBlank(message = "Unit is required")
    private String unit;
    
    @NotBlank(message = "Barcode is required")
    private String barcode;
    
    @NotNull(message = "Cases per pallet is required")
    @Min(value = 1, message = "Cases per pallet must be at least 1")
    private Integer casesPerPallet;
    
    @NotNull(message = "Weight per case is required")
    @DecimalMin(value = "0.0", message = "Weight must be positive")
    private Double weightPerCase;
    
    private boolean active = true;
}