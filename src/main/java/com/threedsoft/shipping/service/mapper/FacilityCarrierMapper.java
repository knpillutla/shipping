package com.threedsoft.shipping.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.threedsoft.shipping.db.FacilityCarrier;
import com.threedsoft.shipping.dto.responses.FacilityCarrierDTO;

/**
 * Mapper for the entity FacilityCarrier and its DTO FacilityCarrierDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FacilityCarrierMapper extends EntityMapper<FacilityCarrierDTO, FacilityCarrier> {



    default FacilityCarrier fromId(Long id) {
        if (id == null) {
            return null;
        }
        FacilityCarrier facilityCarrier = new FacilityCarrier();
        facilityCarrier.setId(id);
        return facilityCarrier;
    }
}
