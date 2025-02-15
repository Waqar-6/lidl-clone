package com.wfarooq.inventorymanagement.mapper;

import com.wfarooq.inventorymanagement.dto.request.BinLocationRequest;
import com.wfarooq.inventorymanagement.dto.response.BinLocationResponse;
import com.wfarooq.inventorymanagement.entity.BinLocation;

public final class BinLocationMapper {
    
    private BinLocationMapper() {}
    
    public static BinLocation mapBinLocationRequestToBinLocation(BinLocationRequest request, BinLocation binLocation) {
        binLocation.setAisleNumber(request.getAisleNumber());
        binLocation.setBinNumber(request.getBinNumber());
        binLocation.setFullLocation(request.getAisleNumber() + "-" + request.getBinNumber());
        binLocation.setMaxPallets(request.getMaxPallets());
        binLocation.setZone(request.getZone());
        return binLocation;
    }



    public static BinLocationResponse mapBinLocationToBinLocationResponse(BinLocation binLocation, BinLocationResponse response) {

        response.setAisleNumber(binLocation.getAisleNumber());
        response.setBinNumber(binLocation.getBinNumber());
        response.setFullLocation(binLocation.getFullLocation());
        response.setMaxPallets(binLocation.getMaxPallets());
        response.setZone(binLocation.getZone());
        response.setCurrentPallets(binLocation.getPallets() != null ? binLocation.getPallets().size() : 0);
        response.setCreatedAt(binLocation.getCreatedAt());
        response.setCreatedBy(binLocation.getCreatedBy());
        response.setUpdatedAt(binLocation.getUpdatedAt());
        response.setUpdatedBy(binLocation.getUpdatedBy());
        return response;
    }
}