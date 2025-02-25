package com.wfarooq.orderservice.dto.response;

import com.wfarooq.orderservice.enums.OrderItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor
public class OrderItemResponse {
    private String productSku;
    private String productName;
    private Integer quantity;
    private String unit;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String department;
    private Integer allocatedStock;
    private OrderItemStatus status;
}