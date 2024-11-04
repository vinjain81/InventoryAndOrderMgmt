package com.bgarage.autopartsmgmt.common.exception.constants;

import com.bgarage.autopartsmgmt.common.exception.dto.Supplier;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public final class VehicleType {

    public static final String TWO_WHEELER = "2-Wheeler";
    public static final String THREE_WHEELER = "3-Wheeler";

    public static final Map<String, Integer> vehicleTypeMap = Map.of(
            TWO_WHEELER, 2,
            THREE_WHEELER, 3
    );

    public static String getVehicleNameById(Integer id) {
        for (Map.Entry<String, Integer> entry : vehicleTypeMap.entrySet()) {
            if (entry.getValue().equals(id)){
                return entry.getKey();
            }
        }

        return StringUtils.EMPTY;
    }

    private VehicleType() { }
}
