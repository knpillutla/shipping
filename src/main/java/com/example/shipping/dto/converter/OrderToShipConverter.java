package com.example.shipping.dto.converter;

import java.util.ArrayList;
import java.util.List;

import com.example.order.dto.events.OrderPlannedEvent;
import com.example.order.dto.events.SmallStoreOrderPlannedEvent;
import com.example.order.dto.responses.OrderDTO;
import com.example.order.dto.responses.OrderLineDTO;
import com.example.shipping.dto.requests.ShipCreationRequestDTO;
import com.example.shipping.dto.requests.ShipLineCreationRequestDTO;

public class OrderToShipConverter {

	public static ShipCreationRequestDTO getShipCreationRequestDTO(SmallStoreOrderPlannedEvent smallOrderPlannedEvent) {
		ShipCreationRequestDTO shipCreationRequestDTO = getShipCreationRequestDTO(smallOrderPlannedEvent.getOrderDTO());
		return shipCreationRequestDTO;
	}
	public static ShipCreationRequestDTO getShipCreationRequestDTO(OrderPlannedEvent orderPlannedEvent) {
		ShipCreationRequestDTO shipCreationRequestDTO = getShipCreationRequestDTO(orderPlannedEvent.getOrderDTO());
		return shipCreationRequestDTO;
	}
	
	public static ShipCreationRequestDTO getShipCreationRequestDTO(OrderDTO orderDTO) {
		ShipCreationRequestDTO shipCreationRequestDTO = new ShipCreationRequestDTO();
		shipCreationRequestDTO.setFirstName(orderDTO.getDelFirstName());
		shipCreationRequestDTO.setLastName(orderDTO.getDelLastName());
		shipCreationRequestDTO.setAddr1(orderDTO.getDelAddr1());
		shipCreationRequestDTO.setAddr2(orderDTO.getDelAddr2());
		shipCreationRequestDTO.setAddr3(orderDTO.getDelAddr3());
		shipCreationRequestDTO.setCity(orderDTO.getDelCity());
		shipCreationRequestDTO.setState(orderDTO.getDelState());
		shipCreationRequestDTO.setCountry(orderDTO.getDelCountry());
		shipCreationRequestDTO.setZipcode(orderDTO.getDelZipcode());
		shipCreationRequestDTO.setBusName(orderDTO.getBusName());
		shipCreationRequestDTO.setLocnNbr(orderDTO.getLocnNbr());
		shipCreationRequestDTO.setCompany(orderDTO.getCompany());
		shipCreationRequestDTO.setDivision(orderDTO.getDivision());
		shipCreationRequestDTO.setBusUnit(orderDTO.getBusUnit());
		shipCreationRequestDTO.setBatchNbr(orderDTO.getBatchNbr());
		shipCreationRequestDTO.setOrderNbr(orderDTO.getOrderNbr());
		shipCreationRequestDTO.setOrderDttm(orderDTO.getOrderDttm());
		shipCreationRequestDTO.setExpectedDeliveryDttm(orderDTO.getExpectedDeliveryDttm());
		shipCreationRequestDTO.setShipByDttm(orderDTO.getShipByDttm());
		shipCreationRequestDTO.setDeliveryType(orderDTO.getDeliveryType());
		shipCreationRequestDTO.setOrderId(orderDTO.getId());
		List<ShipLineCreationRequestDTO> shipLines = new ArrayList();
		for (OrderLineDTO orderLineDTO : orderDTO.getOrderLines()) {
			ShipLineCreationRequestDTO lineReq = new ShipLineCreationRequestDTO(orderLineDTO.getOrderLineNbr(),
					orderLineDTO.getItemBrcd(), orderLineDTO.getOrderQty(), orderLineDTO.getItemWidth(),
					orderLineDTO.getItemHeight(), orderLineDTO.getItemLength(), orderLineDTO.getItemUnitWt(),
					orderLineDTO.getItemUnitVol(), "OrderPlannedEvent", "CreateShip", orderLineDTO.getRefField1(),
					orderLineDTO.getRefField2(), orderLineDTO.getUpdatedDttm(), orderLineDTO.getUpdatedBy());
			shipLines.add(lineReq);
		}
		shipCreationRequestDTO.setShipLines(shipLines);
		return shipCreationRequestDTO;
	}

}
