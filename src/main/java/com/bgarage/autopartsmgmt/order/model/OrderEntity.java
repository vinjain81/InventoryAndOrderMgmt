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
@Table(name = "order_book")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", nullable = false, columnDefinition = "INT")
    private Integer id;

    @Column(name = "SUPPLIER_ID", nullable = false, columnDefinition = "INT")
    private Integer supplierId;

    @UpdateTimestamp
    @Column(name = "ORDER_DATE", nullable = false, columnDefinition = "DATE")
    LocalDate orderDate;
}
