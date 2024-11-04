package com.bgarage.autopartsmgmt.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auto_parts_inventory")
public class AutoPartsInventoryEntity {

    @Id
    @Column(name = "PART_ID", nullable = false, columnDefinition = "INT")
    private Integer partId;

    @Column(name = "AVAILABLE_QTY", nullable = false, columnDefinition = "INT")
    private Integer availableQuantity;

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE", nullable = false, columnDefinition = "DATE")
    LocalDate updateDate;
}
