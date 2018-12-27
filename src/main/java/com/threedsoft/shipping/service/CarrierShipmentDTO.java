package com.threedsoft.shipping.service;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.threedsoft.shipping.db.Ship;
import com.threedsoft.shipping.db.ShipLine;
import com.threedsoft.shipping.service.shipengine.dto.Address;
import com.threedsoft.shipping.service.shipengine.dto.Package;
import com.threedsoft.shipping.service.shipengine.dto.Shipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@NoArgsConstructor
@Data
@AllArgsConstructor
public class CarrierShipmentDTO {
	String carrierName;
	String carrierId;
	String carrierServiceName;
	String packageNbr;
	String orderNbr;
	String trackingNbr;
	String shipCost;
	Double estWt;
	Double actualWt;
	Double shippingRate;
	String deliveryType;
	String shippingLabelFormat;
	byte[] shippingLabel;
}
