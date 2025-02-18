package com.wfarooq.inventorymanagement.controller;

import com.wfarooq.inventorymanagement.dto.response.StockMovementResponse;
import com.wfarooq.inventorymanagement.service.IStockMovementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/stockmovements", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class StockMovementController {

    private final IStockMovementService stockMovementService;
    @GetMapping
    public ResponseEntity<List<StockMovementResponse>> getAllStockMovements () {
        List<StockMovementResponse> res = stockMovementService.fetchAllStockMovements();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("product/{productSku}")
    public ResponseEntity<List<StockMovementResponse>> getStockMovementForProduct (@PathVariable String productSku) {
        List<StockMovementResponse> movements = stockMovementService.fetchMovementsByProductSku(productSku);
        return ResponseEntity.status(HttpStatus.OK).body(movements);
    }

    @GetMapping("/pallet/{huNumber}")
    public ResponseEntity<List<StockMovementResponse>> getStockMovementForPallet (@PathVariable String huNumber) {
        List<StockMovementResponse> movements = stockMovementService.fetchMovementsByPallet(huNumber);
        return ResponseEntity.status(HttpStatus.OK).body(movements);
    }

    @GetMapping("/type/{movementType}")
    public ResponseEntity<List<StockMovementResponse>> getStockMovementForType (@PathVariable String movementType) {
        List<StockMovementResponse> movements = stockMovementService.fetchMovementsByType(movementType);
        return ResponseEntity.status(HttpStatus.OK).body(movements);
    }
}
