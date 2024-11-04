package com.bgarage.autopartsmgmt.order.mapper;

import com.bgarage.autopartsmgmt.common.exception.constants.Suppliers;
import com.bgarage.autopartsmgmt.order.dto.OrderDetailsPerAutoPart;
import com.bgarage.autopartsmgmt.order.model.OrderDetailsEntity;
import com.bgarage.autopartsmgmt.order.model.OrderEntity;
import com.bgarage.autopartsmgmt.order.model.OrderQueueEntity;


public class OrderMapper {

    public static OrderEntity map(OrderDetailsPerAutoPart orderDetailsPerAutoPart) {

        return OrderEntity.builder()
                            .supplierId(Suppliers.suppliersMap.get(orderDetailsPerAutoPart
                            .getSupplierName()).getId())
                        .build();
    }

    public static OrderDetailsEntity map(Integer orderId, Integer partId,
                                         OrderDetailsPerAutoPart orderDetailsPerAutoPart) {

        return OrderDetailsEntity.builder()
                                    .orderId(orderId)
                                    .partId(partId)
                                    .orderQuantity(orderDetailsPerAutoPart.getOrderQty())
                                .build();
    }

    public static OrderEntity map(OrderQueueEntity orderQueueEntity) {

        return OrderEntity.builder()
                .supplierId(orderQueueEntity.getSupplierId())
                .build();
    }

    public static OrderDetailsEntity mapToOrderDetailsEntity(OrderQueueEntity orderQueueEntity) {

        return OrderDetailsEntity.builder()
                        .partId(orderQueueEntity.getPartId())
                        .orderQuantity(orderQueueEntity.getOrderQuantity())
                .build();
    }
}
