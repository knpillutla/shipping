package com.threedsoft.shipping.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.threedsoft.shipping.db.FacilityCarrier;
import com.threedsoft.shipping.db.FacilityCarrierRepository;
import com.threedsoft.shipping.dto.responses.FacilityCarrierDTO;
import com.threedsoft.shipping.service.FacilityCarrierService;
import com.threedsoft.shipping.service.mapper.FacilityCarrierMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Service Implementation for managing FacilityCarrier.
 */
@Service
@Transactional
@Slf4j
public class FacilityCarrierServiceImpl implements FacilityCarrierService {

    private final Logger log = LoggerFactory.getLogger(FacilityCarrierServiceImpl.class);

    @Autowired
    FacilityCarrierRepository facilityCarrierRepository;

    @Autowired
    FacilityCarrierMapper facilityCarrierMapper;

     /**
     * Save a facilityCarrier.
     *
     * @param facilityCarrierDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FacilityCarrierDTO save(FacilityCarrierDTO facilityCarrierDTO) {
        log.debug("Request to save FacilityCarrier : {}", facilityCarrierDTO);

        FacilityCarrier facilityCarrier = facilityCarrierMapper.toEntity(facilityCarrierDTO);
        facilityCarrier = facilityCarrierRepository.save(facilityCarrier);
        return facilityCarrierMapper.toDto(facilityCarrier);
    }

    /**
     * Get all the facilityCarriers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FacilityCarrierDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FacilityCarriers");
        return facilityCarrierRepository.findAll(pageable)
            .map(facilityCarrierMapper::toDto);
    }


    /**
     * Get one facilityCarrier by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FacilityCarrierDTO> findOne(Long id) {
        log.debug("Request to get FacilityCarrier : {}", id);
        return facilityCarrierRepository.findById(id)
            .map(facilityCarrierMapper::toDto);
    }

    /**
     * Delete the facilityCarrier by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FacilityCarrier : {}", id);
        facilityCarrierRepository.deleteById(id);
    }
}
