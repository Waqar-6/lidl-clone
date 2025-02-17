package com.wfarooq.inventorymanagement.controller;


import com.wfarooq.inventorymanagement.constants.BinLocationConstants;
import com.wfarooq.inventorymanagement.constants.StatusConstants;
import com.wfarooq.inventorymanagement.dto.ResponseDto;
import com.wfarooq.inventorymanagement.dto.request.BinLocationRequest;
import com.wfarooq.inventorymanagement.dto.response.BinLocationResponse;
import com.wfarooq.inventorymanagement.service.IBinLocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/binLocations")
@AllArgsConstructor
public class BinLocationController {

    private final IBinLocationService binLocationService;

    @PostMapping
    public ResponseEntity<ResponseDto> createBinLocation (@RequestBody BinLocationRequest request) {
        binLocationService.createBinLocation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(BinLocationConstants.MESSAGE_201, StatusConstants.STATUS_201));
    }

    @GetMapping("/bins")
    public ResponseEntity<List<BinLocationResponse>> getAllBinLocations() {
        List<BinLocationResponse> emptyBins = binLocationService.fetchAllBinLocations();
        return ResponseEntity.status(HttpStatus.OK).body(emptyBins);
    }

    @GetMapping("/emptyBins")
    public ResponseEntity<List<BinLocationResponse>> getEmptyBinLocations() {
        List<BinLocationResponse> emptyBins = binLocationService.fetchAllBinLocations();
        return ResponseEntity.status(HttpStatus.OK).body(emptyBins);
    }

    @GetMapping("/aisle")
    public ResponseEntity<List<BinLocationResponse>> getAllBinLocationsInAisle (@RequestParam String aisle) {
        List<BinLocationResponse> allBinsInAisle = binLocationService.fetchAllBinsByAisle(aisle);
        return ResponseEntity.status(HttpStatus.OK).body(allBinsInAisle);
    }
}
