package com.wfarooq.inventorymanagement.mapper;

import com.wfarooq.inventorymanagement.dto.request.StockMovementRequest;
import com.wfarooq.inventorymanagement.dto.response.StockMovementResponse;
import com.wfarooq.inventorymanagement.entity.StockMovement;

import java.time.LocalDateTime;

public final class StockMovementMapper {
    private StockMovementMapper() {}

    public static StockMovement mapRequestToStockMovement(StockMovementRequest request, StockMovement movement) {
        movement.setCasesQuantity(request.getCasesQuantity());
        movement.setMovementType(request.getMovementType());
        movement.setReference(request.getReference());
        movement.setPickerReference(request.getPickerReference());
        movement.setStatus(request.getStatus());
        movement.setTimestamp(LocalDateTime.now());
        return movement;
    }

    public static StockMovementResponse mapToResponse(StockMovement movement, StockMovementResponse response) {
        response.setProductSku(movement.getProduct().getSku());
        response.setProductName(movement.getProduct().getName());
        response.setPalletHuNumber(movement.getPallet().getHuNumber());
        response.setSourceLocation(movement.getSourceLocation().getFullLocation());
        response.setDestinationLocation(movement.getDestinationLocation().getFullLocation());
        response.setCasesQuantity(movement.getCasesQuantity());
        response.setMovementType(movement.getMovementType());
        response.setReference(movement.getReference());
        response.setPickerReference(movement.getPickerReference());
        response.setTimestamp(movement.getTimestamp());
        response.setStatus(movement.getStatus());
        response.setCreatedAt(movement.getCreatedAt());
        response.setCreatedBy(movement.getCreatedBy());
        response.setUpdatedAt(movement.getUpdatedAt());
        response.setUpdatedBy(movement.getUpdatedBy());
        return response;
    }
}