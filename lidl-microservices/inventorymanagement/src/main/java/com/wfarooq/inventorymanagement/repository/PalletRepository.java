package com.wfarooq.inventorymanagement.repository;

import com.wfarooq.inventorymanagement.entity.Pallet;
import com.wfarooq.inventorymanagement.enums.PalletStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PalletRepository extends JpaRepository<Pallet, UUID> {


    Optional<Pallet> findByHuNumber(String huNumber);
    boolean existsByHuNumber(String huNumber);


    List<Pallet> findByProduct_Sku(String sku);


    List<Pallet> findByCurrentLocation_FullLocation(String fullLocation);


    List<Pallet> findByStatus(PalletStatus status);


    List<Pallet> findByProduct_SkuAndStatus(String sku, PalletStatus status);
    List<Pallet> findByCurrentLocation_FullLocationAndStatus(String fullLocation, PalletStatus status);


    List<Pallet> findByRemainingCasesGreaterThan(Integer cases);
}