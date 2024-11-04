package com.bgarage.autopartsmgmt.autoparts.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auto_parts", uniqueConstraints = @UniqueConstraint(columnNames = {"NAME", "VEHICLE_TYPE", "SUPPLIER_ID"}))
public class AutoPartEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition = "INT")
    private Integer id;

    @Column(name = "NAME", nullable = false, columnDefinition = "VARCHAR(255)")
    String name;

    @Column(name = "VEHICLE_TYPE", nullable = false, columnDefinition = "INT")
    Integer vehicleType;

    @Column(name = "SUPPLIER_ID", nullable = false, columnDefinition = "INT")
    Integer supplierId;

    @Column(name = "MIN_ORDER_QTY", nullable = false, columnDefinition = "INT")
    Integer minOrderQuantity;

    @Column(name = "ORDER_THRESHOLD_LIMIT", nullable = false, columnDefinition = "INT")
    Integer orderThresholdLimit;

    @Override
    public boolean equals(Object o){

        if (this == o){
            return true;
        }

        if(o == null || getClass() != o.getClass()){
            return false;
        }

        AutoPartEntity that = (AutoPartEntity) o;
        return name.equals(that.name) && supplierId.equals(that.supplierId) &&
                vehicleType.equals(that.vehicleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, supplierId, vehicleType);
    }
}
