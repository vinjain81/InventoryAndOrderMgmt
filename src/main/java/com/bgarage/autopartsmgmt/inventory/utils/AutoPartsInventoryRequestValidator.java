package com.bgarage.autopartsmgmt.inventory.utils;

import com.bgarage.autopartsmgmt.common.exception.constants.Suppliers;
import com.bgarage.autopartsmgmt.common.exception.constants.VehicleType;
import com.bgarage.autopartsmgmt.common.exception.appexceptions.BadRequest;
import com.bgarage.autopartsmgmt.inventory.dto.AutoPartsInventoryRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public final class AutoPartsInventoryRequestValidator {

    public static void validateRequest(AutoPartsInventoryRequest autoPartsInventoryRequest){

        if (!VehicleType.vehicleTypeMap.containsKey(autoPartsInventoryRequest.getVehicleType())) {
            throw new BadRequest("Valid vehicle type required.");
        }

        if (!Suppliers.suppliersMap.containsKey(autoPartsInventoryRequest.getSupplierName())) {
            throw new BadRequest("SUPPLIER_A and SUPPLIER_B are the only valid suppliers");
        }
    }
}
