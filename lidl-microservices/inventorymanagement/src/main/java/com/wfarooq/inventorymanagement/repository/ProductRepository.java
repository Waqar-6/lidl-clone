package com.wfarooq.inventorymanagement.repository;

import com.wfarooq.inventorymanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findBySku(String sku);
    boolean existsBySku(String sku);

    Optional<Product> findByBarcode(String barcode);
    boolean existsByBarcode(String barcode);


    Optional<Product> findByName(String name);
    List<Product> findByDepartment (String department);
    List<Product> findByCategory (String category);

}
