package com.threedsoft.shipping.service;

import com.threedsoft.shipping.dto.requests.ShipCreationRequestDTO;
import com.threedsoft.shipping.dto.requests.ShipLineStatusUpdateRequestDTO;
import com.threedsoft.shipping.dto.requests.ShipUpdateRequestDTO;
import com.threedsoft.shipping.dto.responses.ShipResourceDTO;

public interface ShippingService {
	public ShipResourceDTO findById(String busName, Integer locnNbr, Long id) throws Exception;
	public ShipResourceDTO updateShip(ShipUpdateRequestDTO shipUpdRequest) throws Exception;
	ShipResourceDTO createShipForWarehouse(ShipCreationRequestDTO shipCreationRequestDTO) throws Exception;
	ShipResourceDTO createShipForSmallStore(ShipCreationRequestDTO shipCreationRequestDTO) throws Exception;
}