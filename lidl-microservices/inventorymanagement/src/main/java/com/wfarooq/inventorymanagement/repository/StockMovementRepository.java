package com.wfarooq.inventorymanagement.repository;

import com.wfarooq.inventorymanagement.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, UUID> {

    // 📌 1️⃣ Fetch movements by product SKU
    List<StockMovement> findByProduct_Sku(String productSku);

    // 📌 2️⃣ Fetch all movements related to a specific pallet
    List<StockMovement> findByPallet_HuNumber(String huNumber);

    // 📌 3️⃣ Fetch all movements that happened in a specific bin location (either source or destination)
    List<StockMovement> findBySourceLocation_FullLocationOrDestinationLocation_FullLocation(String sourceLocation, String destinationLocation);

    // 📌 4️⃣ Fetch all movements of a specific type (PICK, TRANSFER, RECEIVE, ADJUST)
    List<StockMovement> findByMovementType(String movementType);

    // 📌 5️⃣ Fetch all movements made by a specific picker
    List<StockMovement> findByPickerReference(String pickerReference);
}
