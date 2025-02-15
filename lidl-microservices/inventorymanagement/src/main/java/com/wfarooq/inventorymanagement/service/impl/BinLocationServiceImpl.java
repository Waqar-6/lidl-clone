package com.wfarooq.inventorymanagement.service.impl;

import com.wfarooq.inventorymanagement.dto.request.BinLocationRequest;
import com.wfarooq.inventorymanagement.dto.response.BinLocationResponse;
import com.wfarooq.inventorymanagement.entity.BinLocation;
import com.wfarooq.inventorymanagement.exception.AlreadyExistsException;
import com.wfarooq.inventorymanagement.mapper.BinLocationMapper;
import com.wfarooq.inventorymanagement.repository.BinLocationRepository;
import com.wfarooq.inventorymanagement.service.IBinLocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BinLocationServiceImpl implements IBinLocationService {

    private final BinLocationRepository binLocationRepository;

    /**
     * @param request for new bin location creation
     */
    @Override
    public void createBinLocation(BinLocationRequest request) {
        if (binLocationRepository.existsByAisleNumberAndBinNumber(request.getAisleNumber(), request.getBinNumber()))
            throw new AlreadyExistsException("Bin", "binNumber", request.getBinNumber());
        BinLocation newBin = BinLocationMapper.mapBinLocationRequestToBinLocation(request, new BinLocation());
        newBin.setFullLocation(newBin.getAisleNumber() + "-" + newBin.getBinNumber());
        binLocationRepository.save(newBin);
    }

    /**
     * @return list of empty bin locations
     */
    @Override
    public List<BinLocationResponse> fetchAllEmptyBinLocations() {
        List<BinLocation> binLocations = binLocationRepository.findAll();
        return binLocations.stream().map(bin -> BinLocationMapper.mapBinLocationToBinLocationResponse(bin, new BinLocationResponse())).toList();
    }

    /**
     * @param aisle number
     * @return all bins that belong to that aisle
     */
    @Override
    public List<BinLocationResponse> fetchAllBinsByAisle(String aisle) {
        List<BinLocation> allBinLocationsInThatAisle = binLocationRepository.findByAisleNumber(aisle);
        return allBinLocationsInThatAisle.stream().map(bin -> BinLocationMapper.mapBinLocationToBinLocationResponse(bin, new BinLocationResponse())).toList();
    }
}
