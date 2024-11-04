package com.bgarage.autopartsmgmt.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_queue")
public class OrderQueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition = "INT")
    private Integer id;

    @Column(name = "SUPPLIER_ID", nullable = false, columnDefinition = "INT")
    private Integer supplierId;

    @Column(name = "PART_ID", nullable = false, columnDefinition = "INT")
    private Integer partId;

    @Column(name = "QUANTITY", nullable = false, columnDefinition = "INT")
    private Integer orderQuantity;

    @UpdateTimestamp
    @Column(name = "ORDER_DATE", nullable = false, columnDefinition = "DATE")
    LocalDate orderDate;
}
