package com.bgarage.autopartsmgmt.order.service;

import com.bgarage.autopartsmgmt.autoparts.repository.AutoPartsRepository;
import com.bgarage.autopartsmgmt.common.exception.constants.Suppliers;
import com.bgarage.autopartsmgmt.common.exception.constants.VehicleType;
import com.bgarage.autopartsmgmt.order.dto.OrderDetailsPerAutoPart;
import com.bgarage.autopartsmgmt.order.dto.OrderRequest;
import com.bgarage.autopartsmgmt.order.dto.OrderResponse;
import com.bgarage.autopartsmgmt.order.mapper.OrderMapper;
import com.bgarage.autopartsmgmt.order.model.OrderDetailsEntity;
import com.bgarage.autopartsmgmt.order.model.OrderEntity;
import com.bgarage.autopartsmgmt.order.model.OrderQueueEntity;
import com.bgarage.autopartsmgmt.order.repository.OrderDetailsRepository;
import com.bgarage.autopartsmgmt.order.repository.OrderQueueRepository;
import com.bgarage.autopartsmgmt.order.repository.OrderRepository;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderQueueRepository orderQueueRepository;
    private final AutoPartsRepository autoPartsRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, OrderQueueRepository orderQueueRepository, AutoPartsRepository autoPartsRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderQueueRepository = orderQueueRepository;
        this.autoPartsRepository = autoPartsRepository;
    }

    public OrderResponse placeOrder(OrderRequest orderRequest) {

        OrderResponse orderResponse = OrderResponse.builder().build();

        // Split the request based on suppliers
        log.info("Splitting the order request based on each supplier.");
        ImmutableListMultimap<String, OrderDetailsPerAutoPart> ordersBySuppliers = orderRequest.getOrder().stream()
                .filter(request -> Suppliers.suppliersMap.containsKey(request.getSupplierName()))
                .collect( ImmutableListMultimap.toImmutableListMultimap(
                        OrderDetailsPerAutoPart::getSupplierName, Function.identity()));

        for (String supplier : ordersBySuppliers.keySet()) {
            if (Suppliers.suppliersMap.get(supplier).getProvideDiscount()) {
                log.info("Queuing the orders from supplier-B for later submission to avail discount.");
                orderResponse.setOrdersQueued(queueOrders(ordersBySuppliers.get(supplier)));
            } else {
                orderResponse.setOrderPlaced(insertOrdersInOrderBookAndDetails(ordersBySuppliers.get(supplier)));
            }
        }

        return orderResponse;
    }

    public void placeOrdersFromQueue(OrderEntity orderEntity, List<OrderDetailsEntity> orderDetailsEntities) {

        log.info("Moving orders from queue to order book.");
        OrderEntity saveOrderEntity = insertInOrderBook(orderEntity);

        orderDetailsEntities.forEach(orderDetailsEntity -> {
            orderDetailsEntity.setOrderId(saveOrderEntity.getId());
            insertInOrderDetails(orderDetailsEntity);
        });
    }

    private OrderEntity insertOrdersInOrderBookAndDetails(ImmutableList<OrderDetailsPerAutoPart> orders) {

        OrderDetailsPerAutoPart orderDetailsPerAutoPart = orders.stream().findFirst().orElse(null);
        OrderEntity orderEntity = insertInOrderBook(OrderMapper.map(orderDetailsPerAutoPart));

        orders.forEach( order -> {

            Integer partId = autoPartsRepository.getId(order.getPartName(),
                    VehicleType.vehicleTypeMap.get(order.getVehicleType()),
                    Suppliers.suppliersMap.get(order.getSupplierName()).getId());

            insertInOrderDetails(OrderDetailsEntity.builder()
                        .orderId(orderEntity.getId())
                        .orderQuantity(order.getOrderQty())
                        .partId(partId)
                    .build());
        });

        return orderEntity;
    }

    private OrderEntity insertInOrderBook(OrderEntity orderEntity) {

        log.info("Inserting order in order book table.");
        return orderRepository.save(orderEntity);
    }

    private void insertInOrderDetails(OrderDetailsEntity orderDetailsEntity) {

        log.info("Inserting order details in order details table.");
        orderDetailsRepository.save(orderDetailsEntity);
    }

    private List<OrderQueueEntity> queueOrders(ImmutableList<OrderDetailsPerAutoPart> orders) {

        List<OrderQueueEntity> ordersQueue = new ArrayList<>();
        orders.forEach( order -> {

            Integer partId = autoPartsRepository.getId(order.getPartName(),
                    VehicleType.vehicleTypeMap.get(order.getVehicleType()),
                    Suppliers.suppliersMap.get(order.getSupplierName()).getId());

            ordersQueue.add(orderQueueRepository.save(OrderQueueEntity.builder()
                            .supplierId(Suppliers.suppliersMap.get(order.getSupplierName()).getId())
                            .orderQuantity(order.getOrderQty())
                            .partId(partId)
                            .build())
            );
        });

        return ordersQueue;
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow();
    }
}
