package com.bgarage.autopartsmgmt.order.repository;

import com.bgarage.autopartsmgmt.order.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
