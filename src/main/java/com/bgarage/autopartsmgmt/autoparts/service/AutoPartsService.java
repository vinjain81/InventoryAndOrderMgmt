package com.bgarage.autopartsmgmt.autoparts.service;

import com.bgarage.autopartsmgmt.autoparts.dto.AutoPartRequest;
import com.bgarage.autopartsmgmt.autoparts.mapper.AutoPartsMapper;
import com.bgarage.autopartsmgmt.autoparts.model.AutoPartEntity;
import com.bgarage.autopartsmgmt.autoparts.repository.AutoPartsRepository;
import com.bgarage.autopartsmgmt.autoparts.utils.AutoPartRequestValidator;
import com.bgarage.autopartsmgmt.common.exception.appexceptions.BadRequest;
import com.bgarage.autopartsmgmt.common.exception.appexceptions.ResourceNotFound;
import com.bgarage.autopartsmgmt.common.exception.constants.Suppliers;
import com.bgarage.autopartsmgmt.common.exception.constants.VehicleType;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AutoPartsService
{
    private final AutoPartsRepository autoPartsRepository;

    public AutoPartsService(AutoPartsRepository autoPartsRepository) {
        this.autoPartsRepository = autoPartsRepository;
    }

    public AutoPartEntity addPart(AutoPartRequest autoPartRequest)
    {
        AutoPartRequestValidator.validateRequest(autoPartRequest);
        try{
            log.info("Adding auto part: " + autoPartRequest.getName() + " from supplier: "
                    + autoPartRequest.getSupplierName() + " for vehicle type: " + autoPartRequest.getVehicleType()
                    + "  to DB");
            return autoPartsRepository.save( AutoPartsMapper.map(autoPartRequest));
        } catch(DataIntegrityViolationException ex){
            throw new BadRequest("Part with name, supplier & vehicle type already exist.");
        }
    }

    public AutoPartEntity updatePart(Integer id, AutoPartRequest autoPartRequest)
    {
        AutoPartRequestValidator.validateRequest(autoPartRequest);
        AutoPartEntity updateAutoPartEntity = autoPartsRepository.findById(id).orElseThrow(()
                -> new ResourceNotFound("AutoPart not exist with id: " + id));

        updateAutoPartEntity.setVehicleType(VehicleType.vehicleTypeMap.get(autoPartRequest.getVehicleType()));
        updateAutoPartEntity.setSupplierId(Suppliers.suppliersMap.get(
                autoPartRequest.getSupplierName()).getId());
        updateAutoPartEntity.setName(autoPartRequest.getName());
        updateAutoPartEntity.setMinOrderQuantity(autoPartRequest.getMinOrderQuantity());
        updateAutoPartEntity.setOrderThresholdLimit(autoPartRequest.getMinThresholdQtyTriggerReOrder());

        try{
            log.info("Updating auto part with id: " + id);
            return autoPartsRepository.save( updateAutoPartEntity );
        } catch(DataIntegrityViolationException ex){
            throw new BadRequest("Part with same name, supplier & vehicle type already exist." +
                    " Unique Id is provided for name, supplier & vehicle type combination.");
        }
    }

    public List<AutoPartEntity> getAllAutoParts() {
        return autoPartsRepository.findAll();
    }

    public AutoPartEntity getAutoPartsById(Integer id) {
        return autoPartsRepository.findById(id).orElseThrow();
    }
}
