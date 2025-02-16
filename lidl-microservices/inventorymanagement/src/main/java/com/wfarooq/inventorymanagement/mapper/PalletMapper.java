package com.wfarooq.inventorymanagement.mapper;

import com.wfarooq.inventorymanagement.dto.request.PalletRequest;
import com.wfarooq.inventorymanagement.dto.response.PalletResponse;
import com.wfarooq.inventorymanagement.entity.Pallet;

import java.time.LocalDateTime;

public final class PalletMapper {

    private PalletMapper() {}

    public static Pallet mapPalletRequestToPallet(PalletRequest request, Pallet pallet) {
        pallet.setHuNumber(request.getHuNumber());
        pallet.setTotalCases(request.getTotalCases());
        pallet.setExpiryDate(request.getExpiryDate());
        pallet.setRemainingCases(request.getTotalCases());
        pallet.setStatus(request.getStatus());
        pallet.setReceivedAt(LocalDateTime.now());
        return pallet;
    }

    public static PalletResponse mapPalletToPalletResponse(Pallet pallet, PalletResponse response) {
        response.setHuNumber(pallet.getHuNumber());
        response.setProductSku(pallet.getProduct().getSku());
        response.setProductName(pallet.getProduct().getName());
        response.setTotalCases(pallet.getTotalCases());
        response.setExpiryDate(pallet.getExpiryDate());
        response.setRemainingCases(pallet.getRemainingCases());
        response.setCurrentLocation(pallet.getCurrentLocation().getFullLocation());
        response.setStatus(pallet.getStatus());
        response.setCreatedAt(pallet.getCreatedAt());
        response.setCreatedBy(pallet.getCreatedBy());
        response.setUpdatedAt(pallet.getUpdatedAt());
        response.setUpdatedBy(pallet.getUpdatedBy());
        return response;
    }
}