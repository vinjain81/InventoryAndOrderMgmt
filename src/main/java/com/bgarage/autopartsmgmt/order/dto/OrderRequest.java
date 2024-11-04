package com.bgarage.autopartsmgmt.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotEmpty(message = "Order details required")
    @Schema(description = "Order details")
    private List<OrderDetailsPerAutoPart> Order;
}
