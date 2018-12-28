package com.threedsoft.shipping.endpoint.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.threedsoft.shipping.dto.responses.FacilityCarrierDTO;
import com.threedsoft.shipping.endpoint.rest.errors.BadRequestAlertException;
import com.threedsoft.shipping.service.FacilityCarrierService;
import com.threedsoft.shipping.util.HeaderUtil;
import com.threedsoft.shipping.util.PaginationUtil;

import io.micrometer.core.annotation.Timed;

/**
 * REST controller for managing FacilityCarrier.
 */
@RestController
@RequestMapping("/shipping/v1")
public class FacilityCarrierResource {

	private final Logger log = LoggerFactory.getLogger(FacilityCarrierResource.class);

	private static final String ENTITY_NAME = "shippingFacilityCarrier";

	private final FacilityCarrierService facilityCarrierService;

	public FacilityCarrierResource(FacilityCarrierService facilityCarrierService) {
		this.facilityCarrierService = facilityCarrierService;
	}

	/**
	 * POST /facility-carriers : Create a new facilityCarrier.
	 *
	 * @param facilityCarrierDTO the facilityCarrierDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         facilityCarrierDTO, or with status 400 (Bad Request) if the
	 *         facilityCarrier has already an ID
	 * @throws URISyntaxException       if the Location URI syntax is incorrect
	 * @throws BadRequestAlertException
	 */
	@PostMapping("/facility-carriers")
	@Timed
	public ResponseEntity<FacilityCarrierDTO> createFacilityCarrier(
			@Valid @RequestBody FacilityCarrierDTO facilityCarrierDTO)
			throws URISyntaxException, BadRequestAlertException {
		log.debug("REST request to save FacilityCarrier : {}", facilityCarrierDTO);
		if (facilityCarrierDTO.getId() != null) {
			throw new BadRequestAlertException("A new facilityCarrier cannot already have an ID", ENTITY_NAME,
					"idexists");
		}
		FacilityCarrierDTO result = facilityCarrierService.save(facilityCarrierDTO);
		return ResponseEntity.created(new URI("/api/facility-carriers/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /facility-carriers : Updates an existing facilityCarrier.
	 *
	 * @param facilityCarrierDTO the facilityCarrierDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         facilityCarrierDTO, or with status 400 (Bad Request) if the
	 *         facilityCarrierDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the facilityCarrierDTO couldn't be updated
	 * @throws URISyntaxException       if the Location URI syntax is incorrect
	 * @throws BadRequestAlertException
	 */
	@PostMapping("/facility-carriers/{id}")
	@Timed
	public ResponseEntity<FacilityCarrierDTO> updateFacilityCarrier(@PathVariable("id") Long id,
			@Valid @RequestBody FacilityCarrierDTO facilityCarrierDTO)
			throws URISyntaxException, BadRequestAlertException {
		log.debug("REST request to update FacilityCarrier : {}", facilityCarrierDTO);
		if (facilityCarrierDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		FacilityCarrierDTO result = facilityCarrierService.save(facilityCarrierDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, facilityCarrierDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /facility-carriers : get all the facilityCarriers.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         facilityCarriers in body
	 */
	@GetMapping("/facility-carriers")
	@Timed
	public ResponseEntity<List<FacilityCarrierDTO>> getAllFacilityCarriers(Pageable pageable) {
		log.debug("REST request to get a page of FacilityCarriers");
		Page<FacilityCarrierDTO> page = facilityCarrierService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/facility-carriers");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * GET /facility-carriers/:id : get the "id" facilityCarrier.
	 *
	 * @param id the id of the facilityCarrierDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         facilityCarrierDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/facility-carriers/{id}")
	@Timed
	public ResponseEntity<FacilityCarrierDTO> getFacilityCarrier(@PathVariable Long id) {
		log.debug("REST request to get FacilityCarrier : {}", id);
		Optional<FacilityCarrierDTO> facilityCarrierDTO = facilityCarrierService.findOne(id);
		return ResponseEntity.ok(facilityCarrierDTO.get());
	}

	/**
	 * DELETE /facility-carriers/:id : delete the "id" facilityCarrier.
	 *
	 * @param id the id of the facilityCarrierDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/facility-carriers/{id}")
	@Timed
	public ResponseEntity<Void> deleteFacilityCarrier(@PathVariable Long id) {
		log.debug("REST request to delete FacilityCarrier : {}", id);
		facilityCarrierService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
