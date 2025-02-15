package com.wfarooq.inventorymanagement.repository;

import com.wfarooq.inventorymanagement.entity.BinLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BinLocationRepository extends JpaRepository<BinLocation, UUID> {

    boolean existsByAisleNumberAndBinNumber(String aisleNumber, String binNumber);

    List<BinLocation> findByPalletsIsEmpty();
    List<BinLocation> findByAisleNumber(String aisleNumber);
    Optional<BinLocation> findByFullLocation(String fullLocation);
}
