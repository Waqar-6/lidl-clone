package com.wfarooq.inventorymanagement.mapper;

import com.wfarooq.inventorymanagement.dto.request.StockLevelRequest;
import com.wfarooq.inventorymanagement.dto.response.StockLevelResponse;
import com.wfarooq.inventorymanagement.entity.StockLevel;

public final class StockLevelMapper {
    
    private StockLevelMapper() {}
    
    public static StockLevel mapStockLevelRequestToStockLevel(StockLevelRequest request, StockLevel stockLevel) {
        stockLevel.setMinimumCases(request.getMinimumCases());
        stockLevel.setReorderPoint(request.getReorderPoint());
        return stockLevel;
    }
    
    public static StockLevelResponse mapStockLevelToStockLevelResponse(StockLevel stockLevel, StockLevelResponse response) {
        response.setProductSku(stockLevel.getProduct().getSku());
        response.setProductName(stockLevel.getProduct().getName());
        response.setTotalPallets(stockLevel.getTotalPallets());
        response.setTotalCases(stockLevel.getTotalCases());
        response.setReservedCases(stockLevel.getReservedCases());
        response.setMinimumCases(stockLevel.getMinimumCases());
        response.setReorderPoint(stockLevel.getReorderPoint());
        response.setCreatedAt(stockLevel.getCreatedAt());
        response.setCreatedBy(stockLevel.getCreatedBy());
        response.setUpdatedAt(stockLevel.getUpdatedAt());
        response.setUpdatedBy(stockLevel.getUpdatedBy());
        return response;
    }
}