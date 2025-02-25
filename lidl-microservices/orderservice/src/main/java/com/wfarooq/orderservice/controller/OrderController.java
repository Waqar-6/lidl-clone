package com.wfarooq.orderservice.controller;

import com.wfarooq.orderservice.constants.OrderConstants;
import com.wfarooq.orderservice.constants.StatusConstants;
import com.wfarooq.orderservice.dto.ResponseDto;
import com.wfarooq.orderservice.dto.request.OrderRequest;
import com.wfarooq.orderservice.service.IOrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
