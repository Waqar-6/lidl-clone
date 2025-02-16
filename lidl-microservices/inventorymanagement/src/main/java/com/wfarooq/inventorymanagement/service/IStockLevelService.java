package com.wfarooq.inventorymanagement.service;

import com.wfarooq.inventorymanagement.dto.request.StockLevelRequest;
import com.wfarooq.inventorymanagement.dto.response.StockLevelResponse;

import java.util.List;

public interface IStockLevelService {

    /**
     *
     * @param request for new stock level
     */
    void createStockLevel(StockLevelRequest request);

    /**
     *
     * @return list of all stock levels
     */
    List<StockLevelResponse> fetchAllStockLevels();

    /**
     *
     * @param productSku for fetching stock level for that product
     * @return stock level for that product
     */
    StockLevelResponse fetchStockLevelByProductSku(String productSku);

    /**
     *
     * @param productSku required for updating
     * @param request with updated values
     */
    void updateStockLevel(String productSku, StockLevelRequest request);

    /**
     *
     * @param productSku for product being reserved
     * @param quantity that is being reserved
     */
    void reserveStockForOrder(String productSku, Integer quantity);


    void updateReservedCases(String productSku, Integer reservedCases);

    void releaseReservedStock(String productSku, Integer quantity);

    boolean isStockAvailable(String productSku, Integer quantity);
}