package com.bgarage.autopartsmgmt.order.repository;

import com.bgarage.autopartsmgmt.order.model.OrderQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderQueueRepository extends JpaRepository<OrderQueueEntity, Integer> {
}
