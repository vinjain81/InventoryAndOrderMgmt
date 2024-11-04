package com.bgarage.autopartsmgmt.autoparts.mapper;

import com.bgarage.autopartsmgmt.common.exception.constants.Suppliers;
import com.bgarage.autopartsmgmt.common.exception.constants.VehicleType;
import com.bgarage.autopartsmgmt.autoparts.dto.AutoPartRequest;
import com.bgarage.autopartsmgmt.autoparts.model.AutoPartEntity;

public final class AutoPartsMapper {

    public static AutoPartEntity map(AutoPartRequest autoPartRequest){

        return AutoPartEntity.builder()
                    .vehicleType(VehicleType.vehicleTypeMap.get(autoPartRequest.getVehicleType() ))
                    .name(autoPartRequest.getName())
                    .supplierId(Suppliers.suppliersMap.get(autoPartRequest.getSupplierName()).getId())
                    .minOrderQuantity(autoPartRequest.getMinOrderQuantity())
                    .orderThresholdLimit(autoPartRequest.getMinThresholdQtyTriggerReOrder())
                .build();
    }
}
