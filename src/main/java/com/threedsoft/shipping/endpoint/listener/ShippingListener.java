package com.threedsoft.shipping.endpoint.listener;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.threedsoft.order.dto.events.OrderPlannedEvent;
import com.threedsoft.shipping.service.ShippingService;
import com.threedsoft.shipping.service.mapper.OrderToShipConverter;
import com.threedsoft.shipping.streams.ShippingStreams;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ShippingListener {
	@Autowired
	ShippingService shippingService;

	@StreamListener(target=ShippingStreams.ORDERS_OUTPUT, condition = "headers['eventName']=='OrderPlannedEvent'")
	public void handleOrderPlannedEvent(OrderPlannedEvent orderPlannedEvent) { // OrderCreationRequestDTO
																					// orderCreationRequestDTO) {
		log.info("Received OrderPlannedEvent Msg: {}" + ": at :" + LocalDateTime.now(), orderPlannedEvent);
		long startTime = System.currentTimeMillis();
		try {
			shippingService.createShipForWarehouse(OrderToShipConverter.getShipCreationRequestDTO(orderPlannedEvent));
			long endTime = System.currentTimeMillis();
			log.info("Completed OrderPlannedEvent for : " + orderPlannedEvent + ": at :" + LocalDateTime.now()
					+ " : total time:" + (endTime - startTime) / 1000.00 + " secs");
		} catch (Exception e) {
			e.printStackTrace();
			long endTime = System.currentTimeMillis();
			log.error("Error Completing OrderPlannedEvent for : " + orderPlannedEvent + ": at :"
					+ LocalDateTime.now() + " : total time:" + (endTime - startTime) / 1000.00 + " secs", e);
		}
	}

/*	@StreamListener(target=ShippingStreams.ORDERS_OUTPUT, condition = "headers['eventName']=='SmallStoreOrderPlannedEvent'")
	public void handleSmallStoreOrderPlannedEvent(SmallStoreOrderPlannedEvent smallStoreOrderPlannedEvent) { // OrderCreationRequestDTO
																					// orderCreationRequestDTO) {
		log.info("Received SmallStoreOrderPlannedEvent Msg: {}" + ": at :" + LocalDateTime.now(), smallStoreOrderPlannedEvent);
		long startTime = System.currentTimeMillis();
		try {
			shippingService.createShipForSmallStore(OrderToShipConverter.getShipCreationRequestDTO(smallStoreOrderPlannedEvent));
			long endTime = System.currentTimeMillis();
			log.info("Completed SmallStoreOrderPlannedEvent for : " + smallStoreOrderPlannedEvent + ": at :" + LocalDateTime.now()
					+ " : total time:" + (endTime - startTime) / 1000.00 + " secs");
		} catch (Exception e) {
			e.printStackTrace();
			long endTime = System.currentTimeMillis();
			log.error("Error Completing SmallStoreOrderPlannedEvent for : " + smallStoreOrderPlannedEvent + ": at :"
					+ LocalDateTime.now() + " : total time:" + (endTime - startTime) / 1000.00 + " secs", e);
		}
	}*/

}
