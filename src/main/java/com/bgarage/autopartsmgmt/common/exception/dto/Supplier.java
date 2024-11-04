package com.bgarage.autopartsmgmt.common.exception.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Supplier {

    private Integer id;
    private String name;
    private Boolean provideDiscount;
    private Boolean localSupplier;
}
