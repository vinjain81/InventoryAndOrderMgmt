package com.bgarage.autopartsmgmt.autoparts.repository;

import com.bgarage.autopartsmgmt.autoparts.model.AutoPartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoPartsRepository extends JpaRepository<AutoPartEntity, Integer> {
    @Query("""
            SELECT APE.Id FROM AutoPartEntity APE WHERE APE.name = :name AND
            APE.vehicleType = :vehicleType AND APE.supplierId = :supplierId
            """)
    Integer getId(
            @Param(value = "name") String name,
            @Param(value = "vehicleType") Integer vehicleType,
            @Param(value = "supplierId") Integer supplierId
    );}
