package com.bgarage.autopartsmgmt.order.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
@Table(name = "order_details")
public class OrderDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition = "INT")
    private Integer id;

    @Column(name = "ORDER_ID", nullable = false, columnDefinition = "INT")
    private Integer orderId;

    @Column(name = "PART_ID", nullable = false, columnDefinition = "INT")
    private Integer partId;

    @Column(name = "QUANTITY", nullable = false, columnDefinition = "INT")
    private Integer orderQuantity;
}
