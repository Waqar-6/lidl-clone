package com.wfarooq.inventorymanagement.controller;

import com.wfarooq.inventorymanagement.dto.ResponseDto;
import com.wfarooq.inventorymanagement.dto.response.StockLevelResponse;
import com.wfarooq.inventorymanagement.repository.StockLevelRepository;
import com.wfarooq.inventorymanagement.service.IStockLevelService;
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
@RequestMapping(value = "/stockelevels", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class StockLevelController {

    private final IStockLevelService stockLevelService;

    @GetMapping
    public ResponseEntity<List<StockLevelResponse>> getStockLevels() {
        List<StockLevelResponse> stockList = stockLevelService.fetchAllStockLevels();
        return ResponseEntity.status(HttpStatus.OK).body(stockList);
    }

    @GetMapping("/{productSku}")
    public ResponseEntity<StockLevelResponse> getStockLevelByProductSku(@PathVariable("productSku") String productSku) {
        StockLevelResponse res = stockLevelService.fetchStockLevelByProductSku(productSku);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
