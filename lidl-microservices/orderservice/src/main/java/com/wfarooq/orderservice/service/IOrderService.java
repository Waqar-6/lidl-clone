package com.wfarooq.orderservice.service;

import com.wfarooq.orderservice.dto.request.OrderRequest;
import com.wfarooq.orderservice.dto.response.OrderResponse;
import com.wfarooq.orderservice.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IOrderService {

    // Order Management
    String createOrder(OrderRequest request);
    OrderResponse fetchOrderByNumber(String orderNumber);
    List<OrderResponse> fetchAllOrders();
    List<OrderResponse> fetchOrdersByStore(String storeNumber);
    List<OrderResponse> fetchOrdersByDepartment(String department);
    List<OrderResponse> fetchOrdersByStatus(OrderStatus status);

    // Order Status Management
    void updateOrderStatus(String orderNumber, OrderStatus status);
    void confirmOrder(String orderNumber);
    void cancelOrder(String orderNumber);
    void updateDeliveryTime(String orderNumber, LocalDateTime newDeliveryTime);

    // Order Reporting will complete later
//    Map<String, Object> getOrderStatistics();
//    List<OrderResponse> fetchOrdersInDateRange(LocalDateTime start, LocalDateTime end);
}
