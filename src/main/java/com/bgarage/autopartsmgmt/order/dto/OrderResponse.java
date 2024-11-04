package com.bgarage.autopartsmgmt.order.dto;

import com.bgarage.autopartsmgmt.order.model.OrderEntity;
import com.bgarage.autopartsmgmt.order.model.OrderQueueEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponse {

    @Schema(description = "Order placed")
    private OrderEntity orderPlaced;

    @Schema(description = "Orders queued")
    private List<OrderQueueEntity> ordersQueued;
}
