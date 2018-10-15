package com.threedsoft.shipping.dto.converter;

import java.util.ArrayList;
import java.util.List;

import com.threedsoft.order.dto.events.OrderPlannedEvent;
import com.threedsoft.order.dto.events.SmallStoreOrderPlannedEvent;
import com.threedsoft.order.dto.responses.OrderLineResourceDTO;
import com.threedsoft.order.dto.responses.OrderResourceDTO;
import com.threedsoft.shipping.dto.requests.ShipCreationRequestDTO;
import com.threedsoft.shipping.dto.requests.ShipLineCreationRequestDTO;
import com.threedsoft.util.dto.events.EventResourceConverter;

public class OrderToShipConverter {

	public static ShipCreationRequestDTO getShipCreationRequestDTO(SmallStoreOrderPlannedEvent smallOrderPlannedEvent) {
		ShipCreationRequestDTO shipCreationRequestDTO = getShipCreationRequestDTO((OrderResourceDTO)smallOrderPlannedEvent.getEventResource());
		return shipCreationRequestDTO;
	}
	public static ShipCreationRequestDTO getShipCreationRequestDTO(OrderPlannedEvent orderPlannedEvent) {
		ShipCreationRequestDTO shipCreationRequestDTO = getShipCreationRequestDTO((OrderResourceDTO)EventResourceConverter
				.getObject(orderPlannedEvent.getEventResource(), orderPlannedEvent.getEventResourceClassName()));
		return shipCreationRequestDTO;
	}
	
	public static ShipCreationRequestDTO getShipCreationRequestDTO(OrderResourceDTO orderDTO) {
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
		for (OrderLineResourceDTO orderLineDTO : orderDTO.getOrderLines()) {
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
