package com.wfarooq.inventorymanagement.service;

import com.wfarooq.inventorymanagement.dto.request.BinLocationRequest;
import com.wfarooq.inventorymanagement.dto.response.BinLocationResponse;
import com.wfarooq.inventorymanagement.entity.BinLocation;

import java.util.List;

public interface IBinLocationService {
    /**
     *
     * @param request for new bin location creation
     */
    void createBinLocation(BinLocationRequest request);

    /**
     *
     * @return list of empty bin locations
     */
    List<BinLocationResponse>  fetchAllEmptyBinLocations();

    /**
     *
     * @param aisle number
     * @return all bins that belong to that aisle
     */
    List<BinLocationResponse> fetchAllBinsByAisle (String aisle);
}
