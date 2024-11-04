package com.bgarage.autopartsmgmt.autoparts.utils;

import com.bgarage.autopartsmgmt.common.exception.constants.Suppliers;
import com.bgarage.autopartsmgmt.common.exception.constants.VehicleType;
import com.bgarage.autopartsmgmt.autoparts.dto.AutoPartRequest;
import com.bgarage.autopartsmgmt.common.exception.appexceptions.BadRequest;

import java.util.Objects;

public final class AutoPartRequestValidator {

    public static void validateRequest(AutoPartRequest autoPartRequest){

        if (!VehicleType.vehicleTypeMap.containsKey(autoPartRequest.getVehicleType())) {
            throw new BadRequest("Valid vehicle type required.");
        }

        if (!Suppliers.suppliersMap.containsKey(autoPartRequest.getSupplierName())) {
            throw new BadRequest("SUPPLIER_A and SUPPLIER_B are the only valid suppliers");
        }
    }
}
