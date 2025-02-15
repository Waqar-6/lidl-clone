package com.wfarooq.inventorymanagement.entity;

import com.wfarooq.inventorymanagement.enums.ZoneType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "bin_locations")
public class BinLocation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String aisleNumber;     // e.g., "415"
    private String binNumber;       // e.g., "21"
    private String fullLocation;    // e.g., "415-21"
    private Integer maxPallets;     // Maximum capacity
    
    @Enumerated(EnumType.STRING)
    private ZoneType zone;          // GOODS_IN, PICKING, STORAGE
    
    @OneToMany(mappedBy = "currentLocation")
    private List<Pallet> pallets;
}