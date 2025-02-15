package com.wfarooq.inventorymanagement.service.impl;

import com.wfarooq.inventorymanagement.dto.request.PalletRequest;
import com.wfarooq.inventorymanagement.dto.response.PalletResponse;
import com.wfarooq.inventorymanagement.entity.BinLocation;
import com.wfarooq.inventorymanagement.entity.Pallet;
import com.wfarooq.inventorymanagement.entity.Product;
import com.wfarooq.inventorymanagement.exception.AlreadyExistsException;
import com.wfarooq.inventorymanagement.exception.ResourceNotFoundException;
import com.wfarooq.inventorymanagement.mapper.PalletMapper;
import com.wfarooq.inventorymanagement.repository.BinLocationRepository;
import com.wfarooq.inventorymanagement.repository.PalletRepository;
import com.wfarooq.inventorymanagement.repository.ProductRepository;
import com.wfarooq.inventorymanagement.service.IPalletService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PalletServiceImpl implements IPalletService {

    private PalletRepository palletRepository;
    private ProductRepository productRepository;
    private BinLocationRepository binLocationRepository;


    /**
     * @param request for new pallet
     */
    @Override
    public void createPallet(PalletRequest request) {
        if(palletRepository.existsByHuNumber(request.getHuNumber()))
            throw new AlreadyExistsException("Pallet", "huNumber", request.getHuNumber());
        Pallet newPallet = PalletMapper.mapPalletRequestToPallet(request, new Pallet());
        Product product = productRepository.findBySku(request.getProductSku()).orElseThrow(() -> new ResourceNotFoundException("Product", "sku", request.getProductSku()));
        newPallet.setProduct(product);

        BinLocation binLocation = binLocationRepository.findByFullLocation(request.getBinLocation()).orElseThrow(() -> new ResourceNotFoundException("BinLocation", "fullLocation", request.getBinLocation()));
        newPallet.setCurrentLocation(binLocation);
        palletRepository.save(newPallet);
    }

    /**
     * @return list of all pallets
     */
    @Override
    public List<PalletResponse> fetchAllPallets() {
        List<Pallet> allPallets = palletRepository.findAll();
        return allPallets.stream().map(pallet -> PalletMapper.mapPalletToPalletResponse(pallet, new PalletResponse())).toList();
    }

    /**
     * @param huNumber for pallet being requested
     * @return pallet response object
     */
    @Override
    public PalletResponse fetchPalletByHuNumber(String huNumber) {
        if (!palletRepository.existsByHuNumber(huNumber))
            throw new ResourceNotFoundException("Pallet", "huNumber", huNumber);
        Pallet pallet = palletRepository.findByHuNumber(huNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Pallet", "huNumber", huNumber));
        return PalletMapper.mapPalletToPalletResponse(pallet, new PalletResponse());
    }

    /**
     * @param sku number for pallets
     * @return list of pallets storing product with sku number provided
     */
    @Override
    public List<PalletResponse> fetchPalletsByProductSku(String sku) {
        List<Pallet> pallets = palletRepository.findByProduct_Sku(sku);
        return pallets.stream().map(pallet -> PalletMapper.mapPalletToPalletResponse(pallet, new PalletResponse())).toList();
    }

    /**
     * @param fullLocation where pallets are stored
     * @return list of pallets at that location
     */
    @Override
    public List<PalletResponse> fetchPalletsByLocation(String fullLocation) {
        List<Pallet> pallets = palletRepository.findByCurrentLocation_FullLocation(fullLocation);
        return pallets.stream().map(pallet -> PalletMapper.mapPalletToPalletResponse(pallet, new PalletResponse())).toList();
    }


    /**
     * @param huNumber    of pallet being moved
     * @param newLocation where pallet is being moved to
     */
    @Override
    public void movePalletToLocation(String huNumber, String newLocation) {
        Pallet pallet = palletRepository.findByHuNumber(huNumber).orElseThrow(() -> new ResourceNotFoundException("Pallet", "huNumber", huNumber));
        BinLocation binLocation = binLocationRepository.findByFullLocation(newLocation).orElseThrow(() -> new ResourceNotFoundException("BinLocation", "fullLocation", newLocation));
        pallet.setCurrentLocation(binLocation);
        palletRepository.save(pallet);
    }

    /**
     * @param huNumber     for pallet
     * @param casesRemoved picked cases
     */
    @Override
    public void updatePalletCases(String huNumber, Integer casesRemoved) {
        Pallet pallet = palletRepository.findByHuNumber(huNumber).orElseThrow(() -> new ResourceNotFoundException("Pallet", "huNumber", huNumber));
        pallet.setRemainingCases(pallet.getRemainingCases() - casesRemoved);
        palletRepository.save(pallet);
    }

}
