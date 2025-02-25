package com.wfarooq.orderservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class OrderRequest {

    @NotNull(message = "Store number is required")
    private String storeNumber;

    @NotNull(message = "Requested delivery time is required")
    private LocalDateTime requestedDeliveryTime;

    @NotEmpty(message = "Order must contain at least one item")
    private List<@Valid OrderItemRequest> items;
}
