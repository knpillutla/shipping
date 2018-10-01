package com.example.shipping.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shipping.db.Ship;
import com.example.shipping.db.ShipLine;
import com.example.shipping.db.ShipLineRepository;
import com.example.shipping.db.ShipRepository;
import com.example.shipping.dto.converter.ShipDTOConverter;
import com.example.shipping.dto.events.ShipCreationFailedEvent;
import com.example.shipping.dto.events.ShipRoutingCompletedEvent;
import com.example.shipping.dto.events.ShipUpdateFailedEvent;
import com.example.shipping.dto.requests.ShipCreationRequestDTO;
import com.example.shipping.dto.requests.ShipUpdateRequestDTO;
import com.example.shipping.dto.responses.ShipDTO;
import com.example.util.service.EventPublisher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShippingServiceImpl implements ShippingService {
	@Autowired
	ShipRepository shipDAO;

	@Autowired
	ShipLineRepository shipLineDAO;

	@Autowired
	EventPublisher eventPublisher;

	@Autowired
	ShipDTOConverter shipDTOConverter;

	public enum ShipStatus {
		CREATED(100), ROUTING_COMPLETED(125), SHIPPED(160), CANCELLED(199);
		ShipStatus(Integer statCode) {
			this.statCode = statCode;
		}

		private Integer statCode;

		public Integer getStatCode() {
			return statCode;
		}
	}

	@Override
	@Transactional
	public ShipDTO updateShip(ShipUpdateRequestDTO shipUpdateRequestDTO) throws Exception {
		ShipDTO shipDTO = null;
		try {
			Optional<Ship> shipOptional = shipDAO.findById(shipUpdateRequestDTO.getId());
			if (!shipOptional.isPresent()) {
				throw new Exception("Ship Update Failed. Ship Not found to update");
			}
			Ship shipEntity = shipOptional.get();
			shipEntity.setInvoiceZPL(RandomStringUtils.random(50));
			shipEntity.setLabelZPL(RandomStringUtils.random(50));
			shipEntity.setStatCode(ShipStatus.ROUTING_COMPLETED.getStatCode());
			shipDTO = shipDTOConverter.getShipDTO(shipDAO.save(shipEntity));
			eventPublisher.publish(new ShipRoutingCompletedEvent(shipDTO));
		} catch (Exception ex) {
			log.error("Update Ship Error:" + ex.getMessage(), ex);
			eventPublisher
					.publish(new ShipUpdateFailedEvent(shipUpdateRequestDTO, "Update Ship Error:" + ex.getMessage()));
			throw ex;
		}
		return shipDTO;
	}

	@Override
	public ShipDTO createShipForWarehouse(ShipCreationRequestDTO shipCreationRequestDTO) throws Exception {
		ShipDTO shipDTO = this.createShip(shipCreationRequestDTO);
		// for the time being, both creation and routing are done at the same time until functionality is added.
		ShipUpdateRequestDTO shipUpdateRequestDTO = new ShipUpdateRequestDTO();
		shipUpdateRequestDTO.setId(shipDTO.getId());
		ShipDTO updatedShipDTO = this.updateShip(shipUpdateRequestDTO);
		return updatedShipDTO;
	}

	@Override
	public ShipDTO createShipForSmallStore(ShipCreationRequestDTO shipCreationRequestDTO) throws Exception {
		ShipDTO shipDTO = this.createShip(shipCreationRequestDTO);
		ShipUpdateRequestDTO shipUpdateRequestDTO = new ShipUpdateRequestDTO();
		shipUpdateRequestDTO.setId(shipDTO.getId());
		ShipDTO updatedShipDTO = this.updateShip(shipUpdateRequestDTO);
		return updatedShipDTO;
	}

	@Transactional
	public ShipDTO createShip(ShipCreationRequestDTO shipCreationRequestDTO) throws Exception {
		ShipDTO shipResponseDTO = null;
		try {
			Ship ship = shipDTOConverter.getShipEntity(shipCreationRequestDTO);
			ship.setStatCode(ShipStatus.CREATED.getStatCode());
			LocalDateTime createdDttm = LocalDateTime.now();
			Ship savedShipObj = shipDAO.save(ship);
			shipResponseDTO = shipDTOConverter.getShipDTO(savedShipObj);
			//eventPublisher.publish(new ShipCreatedEvent(shipResponseDTO));
		} catch (Exception ex) {
			log.error("Created Ship Error:" + ex.getMessage(), ex);
			eventPublisher.publish(
					new ShipCreationFailedEvent(shipCreationRequestDTO, "Created Ship Error:" + ex.getMessage()));
			throw ex;
		}
		return shipResponseDTO;
	}

	@Override
	public ShipDTO findById(String busName, Integer locnNbr, Long id) throws Exception {
		Ship shipEntity = shipDAO.findById(busName, locnNbr, id);
		return shipDTOConverter.getShipDTO(shipEntity);
	}

	public ShipLine getShipLine(Ship shipEntity, Long shipDtlId) {
		for (ShipLine shipLine : shipEntity.getShipLines()) {
			if (shipLine.getId() == shipDtlId) {
				return shipLine;
			}
		}
		return null;
	}
}
