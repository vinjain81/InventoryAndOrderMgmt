package com.bgarage.autopartsmgmt.order.service;

import com.bgarage.autopartsmgmt.order.mapper.OrderMapper;
import com.bgarage.autopartsmgmt.order.model.OrderDetailsEntity;
import com.bgarage.autopartsmgmt.order.model.OrderEntity;
import com.bgarage.autopartsmgmt.order.model.OrderQueueEntity;
import com.bgarage.autopartsmgmt.order.repository.OrderQueueRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OrderScheduler {

    private final OrderQueueRepository orderQueueRepository;
    private final OrderService orderService;

    public OrderScheduler(OrderQueueRepository orderQueueRepository, OrderService orderService) {
        this.orderQueueRepository = orderQueueRepository;
        this.orderService = orderService;
    }

    //At 12:30 AM every day orders will be checked in queue and submitted
    @Scheduled(cron = "0 30 0 * * *")
    public void placeScheduledOrders() {

        log.info("Daily schedule triggered to move queued orders to order book.");
        List<OrderQueueEntity> orderQueueEntities = orderQueueRepository.findAll();
        if (!orderQueueEntities.isEmpty())
        {
            OrderEntity orderEntity = OrderMapper.map(orderQueueEntities.stream().findFirst().get());

            List<OrderDetailsEntity> orderDetailsEntities = orderQueueEntities.stream()
                    .map(OrderMapper::mapToOrderDetailsEntity)
                    .collect(Collectors.toList());

            log.info("Starting to move queued orders to order book.");
            orderService.placeOrdersFromQueue(orderEntity, orderDetailsEntities);
            log.info("Moving queued orders to order book completed.");

            log.info("Cleaning order queue.");
            orderQueueRepository.deleteAll(orderQueueEntities);
            log.info("Cleaning order queue completed.");
        }
        log.info("Daily schedule to move queued orders to order book completed.");
    }
}
