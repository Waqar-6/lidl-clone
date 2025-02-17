package com.wfarooq.inventorymanagement.repository;

import com.wfarooq.inventorymanagement.entity.BinLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface BinLocationRepository extends JpaRepository<BinLocation, UUID> {

    boolean existsByAisleNumberAndBinNumber(String aisleNumber, String binNumber);

    List<BinLocation> findByPalletsIsNullOrPalletsIsEmpty();
    List<BinLocation> findByAisleNumber(String aisleNumber);
    Optional<BinLocation> findByFullLocation(String fullLocation);
}
