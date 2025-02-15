package com.wfarooq.inventorymanagement.controller;

import com.wfarooq.inventorymanagement.constants.StatusConstants;
import com.wfarooq.inventorymanagement.dto.ResponseDto;
import com.wfarooq.inventorymanagement.dto.request.PalletRequest;
import com.wfarooq.inventorymanagement.dto.response.PalletResponse;
import com.wfarooq.inventorymanagement.service.IPalletService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pallets", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class PalletController {

    private final IPalletService palletService;

    @PostMapping
    public ResponseEntity<ResponseDto> createPallet (@RequestBody PalletRequest palletRequest) {
        palletService.createPallet(palletRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("Pallet created", StatusConstants.STATUS_201));
    }

    @GetMapping
    public ResponseEntity<List<PalletResponse>> getPallets () {
        List<PalletResponse> pallets = palletService.fetchAllPallets();
        return ResponseEntity.status(HttpStatus.OK).body(pallets);
    }
}
