package com.wfarooq.inventorymanagement.repository;

import com.wfarooq.inventorymanagement.entity.StockLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockLevelRepository extends JpaRepository<StockLevel, UUID> {
    

    Optional<StockLevel> findByProduct_Sku(String productSku);
    

    List<StockLevel> findByReservedCasesGreaterThan(Integer reservedCases);
}