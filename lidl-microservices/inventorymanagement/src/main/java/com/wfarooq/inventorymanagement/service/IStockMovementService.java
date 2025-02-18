package com.wfarooq.inventorymanagement.service;

import com.wfarooq.inventorymanagement.dto.request.StockMovementRequest;
import com.wfarooq.inventorymanagement.dto.response.StockMovementResponse;

import java.util.List;

public interface IStockMovementService {


    // 📌 1️⃣ Record a stock movement (Used for all types: PICK, TRANSFER, RECEIVE, ADJUST)
    void logStockMovement(StockMovementRequest request);

    // 📌 2️⃣ Fetch stock movements for a specific product SKU
    List<StockMovementResponse> fetchMovementsByProductSku(String productSku);
    
    // 📌 3️⃣ Fetch all movements for a specific pallet
    List<StockMovementResponse> fetchMovementsByPallet(String huNumber);

    // 📌 4️⃣ Fetch movements that happened in a specific bin location
    List<StockMovementResponse> fetchMovementsByLocation(String binLocation);

    // 📌 5️⃣ Fetch all movements of a specific type (PICK, TRANSFER, RECEIVE, ADJUST)
    List<StockMovementResponse> fetchMovementsByType(String movementType);

    // 📌 6️⃣ Fetch all stock movements (Useful for reporting & analytics)
    List<StockMovementResponse> fetchAllStockMovements();
}
