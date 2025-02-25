package com.wfarooq.orderservice.service;

import com.wfarooq.orderservice.dto.request.OrderItemRequest;
import com.wfarooq.orderservice.dto.request.OrderRequest;
import com.wfarooq.orderservice.entity.Order;
import com.wfarooq.orderservice.repository.OrderRepository;
import com.wfarooq.orderservice.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @DisplayName(value = "test create order")
    @Test
    void testCreateOrder_whenGivenOrderRequest_shouldReturnOrderNumber () {
        // Arrange
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setStoreNumber("STORE-123");
        orderRequest.setRequestedDeliveryTime(LocalDateTime.now().plusDays(1));

        List<OrderItemRequest> items = new ArrayList<>();
        OrderItemRequest item = new OrderItemRequest();
        item.setProductSku("PRD-123");
        item.setProductName("Test Product");
        item.setQuantity(5);
        item.setUnit("CASE");
        item.setUnitPrice(new BigDecimal("10.00"));
        item.setTotalPrice(new BigDecimal("50.00"));
        item.setDepartment("FRUIT&VEG");
        items.add(item);

        orderRequest.setItems(items);

        // Mock the repository to return true when save is called
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order savedOrder = invocation.getArgument(0);
            // Simulate the database assigning an ID
            Field idField = Order.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(savedOrder, UUID.randomUUID());
            return savedOrder;
        });

        // Act
        String orderNumber = orderService.createOrder(orderRequest);

        // Assert
        assertNotNull(orderNumber);
        assertTrue(orderNumber.startsWith("ORD-"));
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(orderRepository, times(1)).existsByOrderNumber(anyString());
    }

}
