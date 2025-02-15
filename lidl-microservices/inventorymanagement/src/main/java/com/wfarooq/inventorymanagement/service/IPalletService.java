package com.wfarooq.inventorymanagement.service;

import com.wfarooq.inventorymanagement.dto.request.PalletRequest;
import com.wfarooq.inventorymanagement.dto.response.PalletResponse;
import com.wfarooq.inventorymanagement.enums.PalletStatus;

import java.util.List;

public interface IPalletService {

    /**
     *
     * @param request for new pallet
     */
    void createPallet(PalletRequest request);

    /**
     *
     * @return list of all pallets
     */
    List<PalletResponse> fetchAllPallets();

    /**
     *
     * @param huNumber for pallet being requested
     * @return pallet response object
     */
    PalletResponse fetchPalletByHuNumber(String huNumber);

    /**
     *
     * @param sku number for pallets
     * @return list of pallets storing product with sku number provided
     */
    List<PalletResponse> fetchPalletsByProductSku(String sku);

    /**
     *
     * @param fullLocation where pallets are stored
     * @return list of pallets at that location
     */
    List<PalletResponse> fetchPalletsByLocation(String fullLocation);


    /**
     *
     * @param huNumber of pallet being moved
     * @param newLocation where pallet is being moved to
     */
    void movePalletToLocation(String huNumber, String newLocation);

    /**
     *
     * @param huNumber for pallet
     * @param casesRemoved picked cases
     */
    void updatePalletCases(String huNumber, Integer casesRemoved);


}