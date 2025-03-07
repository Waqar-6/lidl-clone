package com.wfarooq.orderservice.controller;

import com.wfarooq.orderservice.constants.OrderConstants;
import com.wfarooq.orderservice.constants.StatusConstants;
import com.wfarooq.orderservice.dto.ResponseDto;
import com.wfarooq.orderservice.dto.request.OrderRequest;
import com.wfarooq.orderservice.dto.response.OrderResponse;
import com.wfarooq.orderservice.enums.OrderStatus;
import com.wfarooq.orderservice.service.IOrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<ResponseDto> createOrder (@Valid @RequestBody OrderRequest request) {
        String res = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(OrderConstants.MESSAGE_201 + " order number: " + res, StatusConstants.STATUS_201));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> fetchAllOrders () {
        List<OrderResponse> allOrders = orderService.fetchAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(allOrders);
    }

    @GetMapping("/orderNumber/{orderNumber}")
    public ResponseEntity<OrderResponse> fetchOrderByOrderNumber (@PathVariable String orderNumber) {
        OrderResponse orderResponse = orderService.fetchOrderByNumber(orderNumber);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    @GetMapping("/storeNumber/{storeNumber}")
    public ResponseEntity<List<OrderResponse>> fetchOrdersByStoreNumber (@PathVariable String storeNumber) {
        List<OrderResponse> orders = orderService.fetchOrdersByStore(storeNumber);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @GetMapping("/orderStatus/{status}")
    public ResponseEntity<List<OrderResponse>> fetchOrdersByStatus (@PathVariable OrderStatus status) {
        List<OrderResponse> orders = orderService.fetchOrdersByStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @PatchMapping("/orderStatusUpdate/{status}")
    public ResponseEntity<ResponseDto> updateOrderStatus(@PathVariable OrderStatus status,@RequestParam String orderNumber) {
        orderService.updateOrderStatus(orderNumber, status);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(OrderConstants.MESSAGE_200_UPDATE, StatusConstants.STATUS_200));
    }
    @PatchMapping("/orderDeliveryTimeUpdate/{newDeliveryTime}")
    public ResponseEntity<ResponseDto> updateDeliveryTime (@RequestParam String orderNumber, @PathVariable LocalDateTime newDeliveryTime) {
        orderService.updateDeliveryTime(orderNumber, newDeliveryTime);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(OrderConstants.MESSAGE_200_UPDATE, StatusConstants.STATUS_200));
    }
}
