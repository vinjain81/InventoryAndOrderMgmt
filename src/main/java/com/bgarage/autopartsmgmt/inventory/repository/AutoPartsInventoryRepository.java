package com.bgarage.autopartsmgmt.inventory.repository;

import com.bgarage.autopartsmgmt.inventory.model.AutoPartsInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoPartsInventoryRepository extends JpaRepository<AutoPartsInventoryEntity, Integer> {


}
