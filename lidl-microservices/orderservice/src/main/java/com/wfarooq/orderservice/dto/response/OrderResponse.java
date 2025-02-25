package com.wfarooq.orderservice.dto.response;

import com.wfarooq.orderservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor
public class OrderResponse {
    private String orderNumber;
    private String storeNumber;
    private OrderStatus status;
    private LocalDateTime requestedDeliveryTime;
    private LocalDateTime estimatedDeliveryTime;
    private List<OrderItemResponse> items;


    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}