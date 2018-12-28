package com.threedsoft.shipping.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.threedsoft.shipping.dto.responses.FacilityCarrierDTO;

/**
 * Service Interface for managing FacilityCarrier.
 */
public interface FacilityCarrierService {

    /**
     * Save a facilityCarrier.
     *
     * @param facilityCarrierDTO the entity to save
     * @return the persisted entity
     */
    FacilityCarrierDTO save(FacilityCarrierDTO facilityCarrierDTO);

    /**
     * Get all the facilityCarriers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FacilityCarrierDTO> findAll(Pageable pageable);


    /**
     * Get the "id" facilityCarrier.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FacilityCarrierDTO> findOne(Long id);

    /**
     * Delete the "id" facilityCarrier.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
