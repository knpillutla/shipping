package com.threedsoft.shipping.dto.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.threedsoft.shipping.db.Ship;
import com.threedsoft.shipping.db.ShipLine;
import com.threedsoft.shipping.dto.requests.ShipCreationRequestDTO;
import com.threedsoft.shipping.dto.requests.ShipLineCreationRequestDTO;
import com.threedsoft.shipping.dto.requests.ShipSearchRequestDTO;
import com.threedsoft.shipping.dto.requests.ShipUpdateRequestDTO;
import com.threedsoft.shipping.dto.responses.ShipLineResourceDTO;
import com.threedsoft.shipping.dto.responses.ShipResourceDTO;

@Component
public class ShipDTOConverter {

	public ShipResourceDTO getShipDTO(Ship shipEntity) {
		List<ShipLineResourceDTO> shipLineDTOList = new ArrayList();
		for (ShipLine shipLine : shipEntity.getShipLines()) {
			ShipLineResourceDTO shipLineDTO = this.getShipLineDTO(shipLine);
			shipLineDTOList.add(shipLineDTO);
		}
		ShipResourceDTO shipDTO = new ShipResourceDTO();
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
		shipDTO.setOrderNbr(shipEntity.getOrderNbr());
		shipDTO.setDeliveryType(shipEntity.getDeliveryType());
		shipDTO.setShipLabelFormat(shipEntity.getShipLabelFormat());
		shipDTO.setShipLabel(shipEntity.getShipLabel());
		shipDTO.setInvoiceLabelFormat(shipEntity.getInvoiceLabelFormat());
		shipDTO.setInvoiceLabel(shipEntity.getInvoiceLabel());
		return shipDTO;
	}

	public ShipLineResourceDTO getShipLineDTO(ShipLine shipLine) {
		ShipLineResourceDTO shipLineDTO = new ShipLineResourceDTO();
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

	public Ship getShipEntityForSearch(ShipSearchRequestDTO invnSearchReq) {
		Ship shipEntity = new Ship();
		shipEntity.setBusName(invnSearchReq.getBusName());
		shipEntity.setLocnNbr(invnSearchReq.getLocnNbr());
		shipEntity.setBusUnit(invnSearchReq.getBusUnit());
		shipEntity.setTrackingNbr(invnSearchReq.getTrackingNbr());
		shipEntity.setOrderNbr(invnSearchReq.getOrderNbr());
		return shipEntity;
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
		LocalDateTime createdDttm = LocalDateTime.now();
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
