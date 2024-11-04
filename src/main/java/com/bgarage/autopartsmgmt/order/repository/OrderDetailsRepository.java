package com.bgarage.autopartsmgmt.order.repository;

import com.bgarage.autopartsmgmt.order.model.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Integer> {
}
