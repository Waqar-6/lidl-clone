package com.wfarooq.orderservice.mapper;

import com.wfarooq.orderservice.dto.request.OrderRequest;
import com.wfarooq.orderservice.dto.response.OrderResponse;
import com.wfarooq.orderservice.entity.Order;

public final class OrderMapper {
    
    private OrderMapper() {}
    
    public static Order mapOrderRequestToOrder(OrderRequest request, Order order) {
        order.setStoreNumber(request.getStoreNumber());
        order.setRequestedDeliveryTime(request.getRequestedDeliveryTime());
        return order;
    }
    
    public static OrderResponse mapOrderToOrderResponse(Order order, OrderResponse response) {
        response.setOrderNumber(order.getOrderNumber());
        response.setStoreNumber(order.getStoreNumber());
        response.setStatus(order.getStatus());
        response.setRequestedDeliveryTime(order.getRequestedDeliveryTime());
        response.setEstimatedDeliveryTime(order.getEstimatedDeliveryTime());
        response.setCreatedAt(order.getCreatedAt());
        response.setCreatedBy(order.getCreatedBy());
        response.setUpdatedAt(order.getUpdatedAt());
        response.setUpdatedBy(order.getUpdatedBy());
        return response;
    }
}