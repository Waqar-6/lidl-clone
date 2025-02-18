package com.wfarooq.inventorymanagement.service.impl;

import com.wfarooq.inventorymanagement.dto.request.StockMovementRequest;
import com.wfarooq.inventorymanagement.dto.response.StockMovementResponse;
import com.wfarooq.inventorymanagement.entity.BinLocation;
import com.wfarooq.inventorymanagement.entity.Pallet;
import com.wfarooq.inventorymanagement.entity.Product;
import com.wfarooq.inventorymanagement.entity.StockMovement;
import com.wfarooq.inventorymanagement.exception.ResourceNotFoundException;
import com.wfarooq.inventorymanagement.mapper.StockMovementMapper;
import com.wfarooq.inventorymanagement.repository.BinLocationRepository;
import com.wfarooq.inventorymanagement.repository.PalletRepository;
import com.wfarooq.inventorymanagement.repository.ProductRepository;
import com.wfarooq.inventorymanagement.repository.StockMovementRepository;
import com.wfarooq.inventorymanagement.service.IStockMovementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class StockMovementServiceImpl implements IStockMovementService {
    private final StockMovementRepository stockMovementRepository;
    private final BinLocationRepository binLocationRepository;
    private final ProductRepository productRepository;
    private final PalletRepository palletRepository;

    @Override
    public void logStockMovement(StockMovementRequest request) {
        StockMovement newMovement = new StockMovement();
        
        Product product = productRepository.findBySku(request.getProductSku())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "sku", request.getProductSku()));
        newMovement.setProduct(product);

        Pallet pallet = palletRepository.findByHuNumber(request.getPalletHuNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Pallet", "huNumber", request.getPalletHuNumber()));
        newMovement.setPallet(pallet);

        if (request.getSourceLocation() != null) {
            BinLocation sourceLocation = binLocationRepository.findByFullLocation(request.getSourceLocation())
                    .orElseThrow(() -> new ResourceNotFoundException("BinLocation", "fullLocation", request.getSourceLocation()));
            newMovement.setSourceLocation(sourceLocation);
        }

        if (request.getDestinationLocation() != null) {
            BinLocation destinationLocation = binLocationRepository.findByFullLocation(request.getDestinationLocation())
                    .orElseThrow(() -> new ResourceNotFoundException("BinLocation", "fullLocation", request.getDestinationLocation()));
            newMovement.setDestinationLocation(destinationLocation);
        }


        newMovement.setCasesQuantity(request.getCasesQuantity());
        newMovement.setMovementType(request.getMovementType());
        newMovement.setReference(request.getReference());
        newMovement.setPickerReference(request.getPickerReference());
        newMovement.setTimestamp(LocalDateTime.now());
        newMovement.setStatus("COMPLETED");


        stockMovementRepository.save(newMovement);
    }

    @Override
    public List<StockMovementResponse> fetchMovementsByProductSku(String productSku) {
        List<StockMovement> movements = stockMovementRepository.findByProduct_Sku(productSku);
        return movements.stream()
                .map(movement -> StockMovementMapper.mapToResponse(movement, new StockMovementResponse()))
                .toList();

    }

    @Override
    public List<StockMovementResponse> fetchMovementsByPallet(String huNumber) {
        List<StockMovement> movements = stockMovementRepository.findByPallet_HuNumber(huNumber);
        return movements.stream()
                .map(movement -> StockMovementMapper.mapToResponse(movement, new StockMovementResponse()))
                .toList();
    }

    @Override
    public List<StockMovementResponse> fetchMovementsByLocation(String binLocation) {
        return List.of();
    }

    @Override
    public List<StockMovementResponse> fetchMovementsByType(String movementType) {
        List<StockMovement> movements = stockMovementRepository.findByMovementType(movementType);
        return movements.stream().map(movement -> StockMovementMapper.mapToResponse(movement, new StockMovementResponse())).toList();
    }

    @Override
    public List<StockMovementResponse> fetchAllStockMovements() {
        List<StockMovement> movements = stockMovementRepository.findAll();
        return movements.stream().map(movement -> StockMovementMapper.mapToResponse(movement, new StockMovementResponse())).toList();
    }
}
