package com.bgarage.autopartsmgmt.inventory.service;

import com.bgarage.autopartsmgmt.autoparts.model.AutoPartEntity;
import com.bgarage.autopartsmgmt.autoparts.repository.AutoPartsRepository;
import com.bgarage.autopartsmgmt.common.exception.appexceptions.ResourceNotFound;
import com.bgarage.autopartsmgmt.common.exception.constants.Suppliers;
import com.bgarage.autopartsmgmt.common.exception.constants.VehicleType;
import com.bgarage.autopartsmgmt.inventory.dto.AutoPartsInventoryRequest;
import com.bgarage.autopartsmgmt.inventory.mapper.AutoPartsInventoryMapper;
import com.bgarage.autopartsmgmt.inventory.model.AutoPartsInventoryEntity;
import com.bgarage.autopartsmgmt.inventory.repository.AutoPartsInventoryRepository;
import com.bgarage.autopartsmgmt.inventory.utils.AutoPartsInventoryRequestValidator;
import com.bgarage.autopartsmgmt.order.dto.OrderDetailsPerAutoPart;
import com.bgarage.autopartsmgmt.order.dto.OrderRequest;
import com.bgarage.autopartsmgmt.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class AutoPartsInventoryService {

    private final AutoPartsRepository autoPartsRepository;
    private final AutoPartsInventoryRepository autoPartsInventoryRepository;
    private final OrderService orderService;

    public AutoPartsInventoryService(AutoPartsRepository autoPartsRepository, AutoPartsInventoryRepository autoPartsInventoryRepository, OrderService orderService) {
        this.autoPartsRepository = autoPartsRepository;
        this.autoPartsInventoryRepository = autoPartsInventoryRepository;
        this.orderService = orderService;
    }

    public AutoPartsInventoryEntity addAvailableQty(AutoPartsInventoryRequest autoPartsInventoryRequest) {

        AutoPartsInventoryRequestValidator.validateRequest(autoPartsInventoryRequest);

        log.info("Fetching part-id for part-name, supplier and vehicle type.");
        Integer partId = autoPartsRepository.getId(autoPartsInventoryRequest.getPartName(),
                VehicleType.vehicleTypeMap.get(autoPartsInventoryRequest.getVehicleType()),
                Suppliers.suppliersMap.get(autoPartsInventoryRequest.getSupplierName()).getId())    ;

        if (Objects.isNull(partId)) {
            throw new ResourceNotFound("Auto part with name " + autoPartsInventoryRequest.getPartName() + " not found.");
        }

        log.info("Adding inventory for part-id = " + partId);
        AutoPartsInventoryEntity updatedAutoPartsInventoryEntity = autoPartsInventoryRepository.save(AutoPartsInventoryMapper.map(partId,
                autoPartsInventoryRequest.getAvailableQuantity()));

        triggerOrderOnBreachingThresholdLimit(updatedAutoPartsInventoryEntity);

        return updatedAutoPartsInventoryEntity;
    }

    public AutoPartsInventoryEntity updateAvailableQty(Integer id, Integer availableQty) {

        boolean isAutoPartExist = autoPartsInventoryRepository.existsById(id);

        if ( !isAutoPartExist ) {
            throw new ResourceNotFound("Auto part with Id " + id + " not found.");
        }

        AutoPartsInventoryEntity updatedAutoPartsInventoryEntity = autoPartsInventoryRepository.save(AutoPartsInventoryMapper.map(id, availableQty));

        triggerOrderOnBreachingThresholdLimit(updatedAutoPartsInventoryEntity);

        return updatedAutoPartsInventoryEntity;
    }

    private void triggerOrderOnBreachingThresholdLimit(AutoPartsInventoryEntity autoPartsInventoryEntity) {

        log.info("Checking for re-order on inventory going below threshold limit for part-id: " + autoPartsInventoryEntity.getPartId());
        Optional<AutoPartEntity> autoPartEntityOptional = autoPartsRepository.findById(autoPartsInventoryEntity.getPartId());
        if (autoPartEntityOptional.isPresent()) {

            AutoPartEntity autoPartEntity = autoPartEntityOptional.get();
            if (autoPartsInventoryEntity.getAvailableQuantity() < autoPartEntity.getOrderThresholdLimit()){

                OrderDetailsPerAutoPart orderDetailsPerAutoPart =
                        OrderDetailsPerAutoPart.builder()
                        .orderQty(autoPartEntity.getMinOrderQuantity())
                        .partName(autoPartEntity.getName())
                        .supplierName(Suppliers.getSupplierNameById(autoPartEntity.getSupplierId()))
                        .vehicleType(VehicleType.getVehicleNameById(autoPartEntity.getVehicleType()))
                        .build();

                log.info("Placing order for part-id: " + autoPartEntity.getId() +
                        " with minimum quantity: " + autoPartEntity.getMinOrderQuantity());
                orderService.placeOrder( OrderRequest.builder()
                                        .Order(List.of(orderDetailsPerAutoPart))
                                        .build());
            }
        }
    }

    public List<AutoPartsInventoryEntity> getAllInventory() {
        return autoPartsInventoryRepository.findAll();
    }

    public AutoPartsInventoryEntity getInventoryByPartId(Integer id) {
        return autoPartsInventoryRepository.findById(id).orElseThrow();
    }
}
