package com.bgarage.autopartsmgmt.autoparts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AutoPartRequest
{
    @NotNull(message = "Part name required")
    @NotBlank(message = "Part name cannot be blank")
    @Schema(description = "Name of the part", example = "wheels")
    private String name;

    @NotNull(message = "Type of vehicle required")
    @NotBlank(message = "vehicle type cannot be blank")
    @Schema(description = "Type of vehicle", example = "2-Wheeler")
    private String vehicleType;

    @NotNull(message = "Minimum order quantity required")
    @Min(value = 1)
    @Schema(description = "Minimum quantity to be ordered to supplier", example = "100")
    private Integer minOrderQuantity;

    @NotNull(message = "Minimum Threshold quantity required")
    @Min(value = 0)
    @Schema(description = "Minimum quantity limit that triggers reorder", example = "10")
    private Integer minThresholdQtyTriggerReOrder;

    @NotNull(message = "Supplier name required")
    @NotBlank(message = "Supplier name cannot be blank")
    @Schema(description = "Name of parts supplier", example = "jbm auto")
    private String supplierName;
}
