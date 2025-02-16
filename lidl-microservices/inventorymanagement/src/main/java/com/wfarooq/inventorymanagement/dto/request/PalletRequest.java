package com.wfarooq.inventorymanagement.dto.request;

import com.wfarooq.inventorymanagement.enums.PalletStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PalletRequest {
    @NotBlank(message = "HU number is required")
    private String huNumber;

    @NotBlank(message = "Product SKU is required")
    private String productSku;

    @NotNull(message = "Total cases is required")
    @Min(value = 1, message = "Total cases must be at least 1")
    private Integer totalCases;

    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in the future")
    private LocalDateTime expiryDate;

    @NotBlank(message = "Bin location is required")
    private String binLocation;

    private PalletStatus status = PalletStatus.RECEIVED;
}