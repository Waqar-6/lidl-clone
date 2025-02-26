package com.wfarooq.orderservice.service.impl;

import com.wfarooq.orderservice.dto.request.OrderRequest;
import com.wfarooq.orderservice.dto.response.OrderItemResponse;
import com.wfarooq.orderservice.dto.response.OrderResponse;
import com.wfarooq.orderservice.entity.Order;
import com.wfarooq.orderservice.entity.OrderItem;
import com.wfarooq.orderservice.enums.OrderItemStatus;
import com.wfarooq.orderservice.enums.OrderStatus;
import com.wfarooq.orderservice.exception.ResourceNotFoundException;
import com.wfarooq.orderservice.mapper.OrderItemMapper;
import com.wfarooq.orderservice.mapper.OrderMapper;
import com.wfarooq.orderservice.repository.OrderRepository;
import com.wfarooq.orderservice.service.IOrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;
@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public String createOrder(OrderRequest request) {
        Order newOrder = OrderMapper.mapOrderRequestToOrder(request, new Order());
        newOrder.setOrderNumber(generateOrderNumber());
        newOrder.setStatus(OrderStatus.PENDING);

        List<OrderItem> orderItems = request.getItems().stream()
                .map(item -> {
                    OrderItem orderItem = OrderItemMapper.mapOrderItemRequestToOrderItem(item, new OrderItem());
                    orderItem.setOrder(newOrder);
                    orderItem.setStatus(OrderItemStatus.PENDING);
                    return orderItem;

                }).toList();
        newOrder.setItems(orderItems);

        newOrder.setTotalPrice(calcOrderTotal(orderItems));
        newOrder.setStatus(OrderStatus.PENDING);
        newOrder.setEstimatedDeliveryTime(LocalDateTime.now());

        orderRepository.save(newOrder);

        return newOrder.getOrderNumber();
    }

    @Override
    public OrderResponse fetchOrderByNumber(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "orderNumber", orderNumber));
        OrderResponse orderResponse = OrderMapper.mapOrderToOrderResponse(order, new OrderResponse());
        List<OrderItemResponse> items = order.getItems().stream().map(item -> OrderItemMapper.mapOrderItemToOrderItemResponse(item, new OrderItemResponse())).toList();
        orderResponse.setItems(items);
        return orderResponse;
    }

    @Override
    public List<OrderResponse> fetchAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> {
            OrderResponse orderRes = OrderMapper.mapOrderToOrderResponse(order, new OrderResponse());
            List<OrderItemResponse> itemRes = order.getItems().stream().map(item -> OrderItemMapper.mapOrderItemToOrderItemResponse(item , new OrderItemResponse())).toList();
            orderRes.setItems(itemRes);
            return orderRes;
        }).toList();

    }

    @Override
    public List<OrderResponse> fetchOrdersByStore(String storeNumber) {
        List<Order> orders = orderRepository.findByStoreNumber(storeNumber);

        return orders.stream().map(order -> {
            OrderResponse orderResponse = OrderMapper.mapOrderToOrderResponse(order, new OrderResponse());
            List<OrderItemResponse> items = order.getItems().stream().map(item -> OrderItemMapper.mapOrderItemToOrderItemResponse(item, new OrderItemResponse())).toList();
            orderResponse.setItems(items);
            return orderResponse;
        }).toList();
    }

    @Override
    public List<OrderResponse> fetchOrdersByDepartment(String department) {
        return List.of();
    }

    @Override
    public List<OrderResponse> fetchOrdersByStatus(OrderStatus status) {
        return List.of();
    }

    @Override
    public void updateOrderStatus(String orderNumber, OrderStatus status) {

    }

    @Override
    public void confirmOrder(String orderNumber) {

    }

    @Override
    public void cancelOrder(String orderNumber) {

    }

    @Override
    public void updateDeliveryTime(String orderNumber, LocalDateTime newDeliveryTime) {

    }

    // creates random order number
    private String generateOrderNumber() {
        LocalDate today = LocalDate.now();
        String datePart = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // Generate a random 5-digit number
        int randomNum = 10000 + new Random().nextInt(90000);

        // Create order number in format ORD-YYYYMMDD-XXXXX
        String orderNumber = String.format("ORD-%s-%d", datePart, randomNum);

        // Check if the generated number already exists
        while (orderRepository.existsByOrderNumber(orderNumber)) {
            // If it exists, generate a new random number
            randomNum = 10000 + new Random().nextInt(90000);
            orderNumber = String.format("ORD-%s-%d", datePart, randomNum);
        }

        return orderNumber;
    }

    private BigDecimal calcOrderTotal (List<OrderItem> items) {
        return items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
