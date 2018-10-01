package com.example.shipping.dto.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.shipping.db.Ship;
import com.example.shipping.db.ShipLine;
import com.example.shipping.dto.requests.ShipCreationRequestDTO;
import com.example.shipping.dto.requests.ShipLineCreationRequestDTO;
import com.example.shipping.dto.requests.ShipUpdateRequestDTO;
import com.example.shipping.dto.responses.ShipDTO;
import com.example.shipping.dto.responses.ShipLineDTO;

@Component
public class ShipDTOConverter {

	public ShipDTO getShipDTO(Ship shipEntity) {
		List<ShipLineDTO> shipLineDTOList = new ArrayList();
		for (ShipLine shipLine : shipEntity.getShipLines()) {
			ShipLineDTO shipLineDTO = this.getShipLineDTO(shipLine);
			shipLineDTOList.add(shipLineDTO);
		}
		ShipDTO shipDTO = new ShipDTO();
		shipDTO.setId(shipEntity.getId());
		shipDTO.setBusName(shipEntity.getBusName());
		shipDTO.setLocnNbr(shipEntity.getLocnNbr());
		shipDTO.setCompany(shipEntity.getCompany());
		shipDTO.setDivision(shipEntity.getDivision());
		shipDTO.setBusUnit(shipEntity.getBusUnit());
		shipDTO.setStatCode(shipEntity.getStatCode());
		shipDTO.setShipByDttm(shipEntity.getShipByDttm());
		shipDTO.setExpectedDeliveryDttm(shipEntity.getExpectedDeliveryDttm());
		shipDTO.setOrderDttm(shipEntity.getOrderDttm());
		shipDTO.setUpdatedBy(shipEntity.getUpdatedBy());
		shipDTO.setShipCarrier(shipEntity.getShipCarrier());
		shipDTO.setShipCarrierService(shipEntity.getShipCarrierService());
		shipDTO.setShipCost(shipEntity.getShipCost());
		shipDTO.setTrackingNbr(shipEntity.getTrackingNbr());
		shipDTO.setOrderId(shipEntity.getOrderId());
		return shipDTO;
	}

	public ShipLineDTO getShipLineDTO(ShipLine shipLine) {
		ShipLineDTO shipLineDTO = new ShipLineDTO();
		shipLineDTO.setId(shipLine.getId());
		shipLineDTO.setShipLineNbr(shipLine.getShipLineNbr());
		shipLineDTO.setItemBrcd(shipLine.getItemBrcd());
		shipLineDTO.setItemHeight(shipLine.getItemHeight());
		shipLineDTO.setItemLength(shipLine.getItemLength());
		shipLineDTO.setItemWidth(shipLine.getItemWidth());
		shipLineDTO.setItemUnitVol(shipLine.getItemUnitVol());
		shipLineDTO.setItemUnitWt(shipLine.getItemUnitWt());
		shipLineDTO.setQty(shipLine.getQty());
		shipLineDTO.setRefField1(shipLine.getRefField1());
		shipLineDTO.setRefField2(shipLine.getRefField2());
		shipLineDTO.setUpdatedBy(shipLine.getUpdatedBy());
		return shipLineDTO;
	}

	public Ship getShipEntity(ShipCreationRequestDTO shipCreationRequestDTO) {
		Ship shipEntity = new Ship();
		shipEntity.setFirstName(shipCreationRequestDTO.getFirstName());
		shipEntity.setLastName(shipCreationRequestDTO.getLastName());
		shipEntity.setAddr1(shipCreationRequestDTO.getAddr1());
		shipEntity.setAddr2(shipCreationRequestDTO.getAddr2());
		shipEntity.setAddr3(shipCreationRequestDTO.getAddr3());
		shipEntity.setCity(shipCreationRequestDTO.getCity());
		shipEntity.setState(shipCreationRequestDTO.getState());
		shipEntity.setCountry(shipCreationRequestDTO.getCountry());
		shipEntity.setZipcode(shipCreationRequestDTO.getZipcode());
		shipEntity.setBusName(shipCreationRequestDTO.getBusName());
		shipEntity.setLocnNbr(shipCreationRequestDTO.getLocnNbr());
		shipEntity.setCompany(shipCreationRequestDTO.getCompany());
		shipEntity.setDivision(shipCreationRequestDTO.getDivision());
		shipEntity.setBusUnit(shipCreationRequestDTO.getBusUnit());
		shipEntity.setBatchNbr(shipCreationRequestDTO.getBatchNbr());
		shipEntity.setOrderNbr(shipCreationRequestDTO.getOrderNbr());
		shipEntity.setOrderDttm(shipCreationRequestDTO.getOrderDttm());
		shipEntity.setExpectedDeliveryDttm(shipCreationRequestDTO.getExpectedDeliveryDttm());
		shipEntity.setShipByDttm(shipCreationRequestDTO.getShipByDttm());
		shipEntity.setDeliveryType(shipCreationRequestDTO.getDeliveryType());
		shipEntity.setOrderId(shipCreationRequestDTO.getOrderId());
		Date createdDttm = new java.util.Date();
		List<ShipLine> shipLineList = new ArrayList();
		for (ShipLineCreationRequestDTO shipLineCreationRequestDTO : shipCreationRequestDTO.getShipLines()) {
			ShipLine shipLineEntity = getShipLineEntity(shipLineCreationRequestDTO, shipCreationRequestDTO);
			shipEntity.addShipLine(shipLineEntity);
			shipLineEntity.setShip(shipEntity);
		}
		return shipEntity;
	}

	public ShipLine getShipLineEntity(ShipLineCreationRequestDTO shipLineCreationRequestDTO,
			ShipCreationRequestDTO shipCreationRequestDTO) {
		ShipLine shipLine = new ShipLine();
		shipLine.setItemBrcd(shipLineCreationRequestDTO.getItemBrcd());
		shipLine.setItemHeight(shipLineCreationRequestDTO.getItemHeight());
		shipLine.setItemLength(shipLineCreationRequestDTO.getItemLength());
		shipLine.setItemWidth(shipLineCreationRequestDTO.getItemWidth());
		shipLine.setItemUnitVol(shipLineCreationRequestDTO.getItemUnitVol());
		shipLine.setItemUnitWt(shipLineCreationRequestDTO.getItemUnitWt());
		shipLine.setShipLineNbr(shipLineCreationRequestDTO.getShipLineNbr());
		shipLine.setQty(shipLineCreationRequestDTO.getQty());
		return shipLine;
	}

	public Ship updateShipEntity(Ship shipEntity, ShipUpdateRequestDTO shipUpdateReqDTO) {
		shipEntity.setExpectedDeliveryDttm(shipUpdateReqDTO.getExpectedDeliveryDttm());
		shipEntity.setDeliveryType(shipUpdateReqDTO.getDeliveryType());
		shipEntity.setShipByDttm(shipUpdateReqDTO.getShipByDttm());
		shipEntity.setTransactionName(shipUpdateReqDTO.getTransactionName());
		shipEntity.setUpdatedBy(shipUpdateReqDTO.getUserId());
		shipEntity.setRefField1(shipUpdateReqDTO.getRefField1());
		shipEntity.setRefField2(shipUpdateReqDTO.getRefField2());
		shipEntity.setSource(shipUpdateReqDTO.getSource());
		return shipEntity;
	}


}
