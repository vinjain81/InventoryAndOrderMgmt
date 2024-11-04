package com.bgarage.autopartsmgmt.inventory.mapper;

import com.bgarage.autopartsmgmt.inventory.model.AutoPartsInventoryEntity;

public final class AutoPartsInventoryMapper {

    public static AutoPartsInventoryEntity map(Integer partId, Integer availableQty){

        return  AutoPartsInventoryEntity.builder()
                    .partId(partId)
                    .availableQuantity(availableQty)
                .build();
    }
}
