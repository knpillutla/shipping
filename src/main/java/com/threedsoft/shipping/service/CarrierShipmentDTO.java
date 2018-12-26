package com.threedsoft.shipping.service;

import java.time.LocalDateTime;
import java.util.List;

import com.threedsoft.shipping.db.Ship;
import com.threedsoft.shipping.db.ShipLine;

import lombok.Data;

@Data
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
