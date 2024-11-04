package com.bgarage.autopartsmgmt.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AutoPartsInventoryRequest {

    @NotNull(message = "Part name required")
    @NotBlank(message = "Part name cannot be blank")
    @Schema(description = "Name of the part", example = "wheels")
    private String partName;

    @NotNull(message = "Supplier name required")
    @NotBlank(message = "Supplier name cannot be blank")
    @Schema(description = "Name of the part supplier", example = "jbm auto")
    private String supplierName;

    @NotNull(message = "Type of vehicle required")
    @NotBlank(message = "vehicle type cannot be blank")
    @Schema(description = "Type of vehicle", example = "2-Wheeler")
    private String vehicleType;

    @NotNull(message = "Available quantity of the part required")
    @Min(value = 0)
    @Schema(description = "Available quantity of the part", example = "100")
    private Integer availableQuantity;
}
