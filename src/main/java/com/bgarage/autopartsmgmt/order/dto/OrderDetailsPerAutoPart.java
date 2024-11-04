package com.bgarage.autopartsmgmt.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderDetailsPerAutoPart {

    @NotNull(message = "Part name required")
    @NotBlank(message = "Part name cannot be blank")
    @Schema(description = "Name of the part", example = "wheels")
    private String partName;

    @NotNull(message = "Type of vehicle required")
    @NotBlank(message = "vehicle type cannot be blank")
    @Schema(description = "Type of vehicle", example = "2-Wheeler")
    private String vehicleType;

    @NotNull(message = "Supplier name required")
    @NotBlank(message = "Supplier name cannot be blank")
    @Schema(description = "Name of parts supplier", example = "jbm auto")
    private String supplierName;

    @NotNull(message = "Order quantity of the part required")
    @Min(value = 1)
    @Schema(description = "Order quantity of parts", example = "100")
    private Integer orderQty;
}
