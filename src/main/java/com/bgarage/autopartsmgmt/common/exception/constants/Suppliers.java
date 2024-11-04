package com.bgarage.autopartsmgmt.common.exception.constants;

import com.bgarage.autopartsmgmt.common.exception.dto.Supplier;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public final class Suppliers {

   public static final Map<String, Supplier> suppliersMap = Map.of(
            "SUPPLIER_A",  Supplier.builder()
                   .name("SUPPLIER_A")
                   .localSupplier(true)
                   .id(1)
                   .provideDiscount(false)
                   .build(),
            "SUPPLIER_B", Supplier.builder()
                   .name("SUPPLIER_B")
                   .localSupplier(false)
                   .id(2)
                   .provideDiscount(true)
                   .build()
    );

   public static String getSupplierNameById(Integer id) {

       for (Map.Entry<String,Supplier> entry : suppliersMap.entrySet()) {
           if (entry.getValue().getId().equals(id)){
               return entry.getValue().getName();
           }
       }

       return StringUtils.EMPTY;
   }

    private Suppliers() { }
}
