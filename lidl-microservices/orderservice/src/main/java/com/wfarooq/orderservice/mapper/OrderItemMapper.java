package com.wfarooq.orderservice.mapper;

import com.wfarooq.orderservice.dto.request.OrderItemRequest;
import com.wfarooq.orderservice.dto.response.OrderItemResponse;
import com.wfarooq.orderservice.entity.OrderItem;

import java.math.BigDecimal;

public final class OrderItemMapper {
    
    private OrderItemMapper() {}
    
    public static OrderItem mapOrderItemRequestToOrderItem(OrderItemRequest request, OrderItem item) {
        item.setProductSku(request.getProductSku());
        item.setProductName(request.getProductName());
        item.setQuantity(request.getQuantity());
        item.setUnit(request.getUnit());
        item.setUnitPrice(request.getUnitPrice());
        item.setTotalPrice(request.getTotalPrice());
        item.setDepartment(request.getDepartment());
        return item;
    }
    
    public static OrderItemResponse mapOrderItemToOrderItemResponse(OrderItem item, OrderItemResponse response) {
        response.setProductSku(item.getProductSku());
        response.setProductName(item.getProductName());
        response.setQuantity(item.getQuantity());
        response.setUnit(item.getUnit());
        response.setUnitPrice(item.getUnitPrice());
        response.setTotalPrice(item.getTotalPrice());
        response.setDepartment(item.getDepartment());
        response.setAllocatedStock(item.getAllocatedStock());
        response.setStatus(item.getStatus());
        return response;
    }
}