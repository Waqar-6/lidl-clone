package com.wfarooq.inventorymanagement.service.impl;

import com.wfarooq.inventorymanagement.dto.request.StockLevelRequest;
import com.wfarooq.inventorymanagement.dto.response.StockLevelResponse;
import com.wfarooq.inventorymanagement.entity.StockLevel;
import com.wfarooq.inventorymanagement.exception.ResourceNotFoundException;
import com.wfarooq.inventorymanagement.mapper.StockLevelMapper;
import com.wfarooq.inventorymanagement.repository.StockLevelRepository;
import com.wfarooq.inventorymanagement.service.IStockLevelService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StockLevelServiceImpl implements IStockLevelService {

    private final StockLevelRepository stockLevelRepository;
    /**
     * @param request for new stock level
     */
    @Override
    public void createStockLevel(StockLevelRequest request) {
        StockLevel newStockLevel = StockLevelMapper.mapStockLevelRequestToStockLevel(request, new StockLevel());
        stockLevelRepository.save(newStockLevel);
    }

    /**
     * @return list of all stock levels
     */
    @Override
    public List<StockLevelResponse> fetchAllStockLevels() {
        List<StockLevel> stockLevels = stockLevelRepository.findAll();
        return stockLevels.stream().map(stock -> {
            StockLevelResponse res = StockLevelMapper.mapStockLevelToStockLevelResponse(stock, new StockLevelResponse());
            res.setNeedsReorder(stock.getTotalCases() <= stock.getReorderPoint());
            return res;
        }).toList();
    }

    /**
     * @param productSku for fetching stock level for that product
     * @return stock level for that product
     */
    @Override
    public StockLevelResponse fetchStockLevelByProductSku(String productSku) {
        StockLevel stockLevel = stockLevelRepository.findByProduct_Sku(productSku)
                .orElseThrow(() -> new ResourceNotFoundException("StockLevel", "productSku", productSku));

        return StockLevelMapper.mapStockLevelToStockLevelResponse(stockLevel, new StockLevelResponse());
    }


    /**
     * @param productSku required for updating
     * @param request    with updated values
     */
    @Override
    @Transactional
    public void updateStockLevel(String productSku, StockLevelRequest request) {
        StockLevel stockLevel = stockLevelRepository.findByProduct_Sku(productSku)
                .orElseThrow(() -> new ResourceNotFoundException("StockLevel", "productSku", productSku));

        // Update only if the values are provided in the request
        if (request.getMinimumCases() != null) {
            stockLevel.setMinimumCases(request.getMinimumCases());
        }
        if (request.getReorderPoint() != null) {
            stockLevel.setReorderPoint(request.getReorderPoint());
        }
        if (request.getTotalCases() != null && request.getTotalCases() >= 0) {
            stockLevel.setTotalCases(request.getTotalCases());
        } else {
            throw new IllegalArgumentException("Total cases cannot be negative");
        }

        stockLevelRepository.save(stockLevel);
    }


    /**
     * @param productSku for product being reserved
     * @param quantity   that is being reserved
     */
    @Override
    @Transactional
    public void reserveStockForOrder(String productSku, Integer quantity) {
        // Find the Stock Level
        StockLevel stockLevel = stockLevelRepository.findByProduct_Sku(productSku)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "sku", productSku));

        // Ensure enough stock is available
        if (stockLevel.getTotalCases() < quantity) {
            throw new IllegalArgumentException("Not enough stock available to reserve!");
        }

        // Move stock from available to reserved
        stockLevel.setReservedCases(stockLevel.getReservedCases() + quantity);
        stockLevel.setTotalCases(stockLevel.getTotalCases() - quantity);

        // Save changes
        stockLevelRepository.save(stockLevel);
    }


    @Override
    @Transactional
    public void updateReservedCases(String productSku, Integer reservedCases) {
        StockLevel stockLevel = stockLevelRepository.findByProduct_Sku(productSku)
                .orElseThrow(() -> new ResourceNotFoundException("StockLevel", "productSku", productSku));

        if (reservedCases < 0 || reservedCases > stockLevel.getTotalCases()) {
            throw new IllegalArgumentException("Invalid reserved case amount");
        }

        stockLevel.setReservedCases(reservedCases);
        stockLevelRepository.save(stockLevel);
    }

    @Override
    @Transactional
    public void releaseReservedStock(String productSku, Integer quantity) {
        StockLevel stockLevel = stockLevelRepository.findByProduct_Sku(productSku)
                .orElseThrow(() -> new ResourceNotFoundException("StockLevel", "productSku", productSku));

        if (stockLevel.getReservedCases() < quantity) {
            throw new IllegalArgumentException("Not enough reserved stock to release");
        }

        stockLevel.setReservedCases(stockLevel.getReservedCases() - quantity);
        stockLevel.setTotalCases(stockLevel.getTotalCases() + quantity);
        stockLevelRepository.save(stockLevel);
    }

    @Override
    public boolean isStockAvailable(String productSku, Integer quantity) {
        StockLevel stockLevel = stockLevelRepository.findByProduct_Sku(productSku)
                .orElseThrow(() -> new ResourceNotFoundException("StockLevel", "productSku", productSku));

        return stockLevel.getTotalCases() >= quantity;
    }



}
