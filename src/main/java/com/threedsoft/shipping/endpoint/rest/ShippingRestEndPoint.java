package com.threedsoft.shipping.endpoint.rest;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.threedsoft.shipping.dto.events.ShipCreationFailedEvent;
import com.threedsoft.shipping.dto.events.ShipUpdateFailedEvent;
import com.threedsoft.shipping.dto.requests.ShipCreationRequestDTO;
import com.threedsoft.shipping.dto.requests.ShipSearchRequestDTO;
import com.threedsoft.shipping.dto.requests.ShipUpdateRequestDTO;
import com.threedsoft.shipping.service.ShippingService;
import com.threedsoft.shipping.util.ShippingConstants;
import com.threedsoft.util.dto.ErrorResourceDTO;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/shipping/v1")
@Api(value = "Shipping Service", description = "Operations pertaining to Shipping")
@RefreshScope
@Slf4j
public class ShippingRestEndPoint {
	@Autowired
	ShippingService shipService;

	@Value("${wms.service.health.msg: Shipping Service - Config Server is not working..please check}")
	private String healthMsg;

	@Value("${wms.service.ready.msg: Shipping Service - Not ready yet}")
	private String readyMsg;

	@GetMapping("/ready")
	public ResponseEntity ready() throws Exception {
		return ResponseEntity.ok(readyMsg);
	}

	@GetMapping("/health")
	public ResponseEntity health() throws Exception {
		return ResponseEntity.ok(healthMsg);
	}

	@GetMapping("/{busName}/{locnNbr}/ships/{id}")
	public ResponseEntity getById(@PathVariable("busName") String busName, @PathVariable("locnNbr") Integer locnNbr,
			@PathVariable("id") Long id) throws IOException {
		try {
			return ResponseEntity.ok(shipService.findById(busName, locnNbr, id));
		} catch (Exception e) {
			log.error("Error Occured for busName:" + busName + ",locnNbr:" + locnNbr + ", id:" + id + " : "
					+ e.getMessage());
			return ResponseEntity.badRequest()
					.body(new ErrorRestResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
							"Error Occured for GET request busName:" + busName + ",locnNbr:" + locnNbr + ", id:" + id
									+ " : " + e.getMessage()));
		}
	}

	@GetMapping("/{busName}/{locnNbr}/ships")
	public ResponseEntity getList(@PathVariable("busName") String busName, @PathVariable("locnNbr") Integer locnNbr)
			throws IOException {
		try {
			return ResponseEntity.ok(shipService.findByBusNameAndLocnNbr(busName, locnNbr));
		} catch (Exception e) {
			log.error("Error Occured for busName:" + busName + ", locnNbr:" + locnNbr + " : " + e.getMessage());
			return ResponseEntity.badRequest()
					.body(new ErrorRestResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
							"Error Occured for GET request busName:" + busName + ", locnNbr:" + locnNbr + " : "
									+ e.getMessage()));
		}
	}

	@PostMapping("/{busName}/{locnNbr}/ships/{id}")
	public ResponseEntity updateShip(@PathVariable("busName") String busName, @PathVariable("locnNbr") Integer locnNbr,
			@RequestBody ShipUpdateRequestDTO shipUpdateReq) throws IOException {
		try {
			return ResponseEntity.ok(shipService.updateShip(shipUpdateReq));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest()
					.body(new ShipUpdateFailedEvent(shipUpdateReq, ShippingConstants.SHIPPING_SERVICE_NAME,
							"Error Occured while processing request:" + e.getMessage()));
		}
	}

	@PostMapping("/{busName}/{locnNbr}/ships/smallstore")
	public ResponseEntity createShipForSmallStore(@PathVariable("busName") String busName,
			@PathVariable("locnNbr") Integer locnNbr, @RequestBody ShipCreationRequestDTO shipCreationReq)
			throws IOException {
		long startTime = System.currentTimeMillis();
		log.info("Received Ship Create request for : " + shipCreationReq.toString() + ": at :" + LocalDateTime.now());
		ResponseEntity resEntity = null;
		try {
			resEntity = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
					.body(shipService.createShipForSmallStore(shipCreationReq));
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = ResponseEntity.badRequest()
					.body(new ShipCreationFailedEvent(shipCreationReq, ShippingConstants.SHIPPING_SERVICE_NAME,
							"Error Occured while processing shipping Create request:" + e.getMessage()));
		}
		long endTime = System.currentTimeMillis();
		log.info("Completed Ship Create request for : " + shipCreationReq.toString() + ": at :" + LocalDateTime.now()
				+ " : total time:" + (endTime - startTime) / 1000.00 + " secs");
		return resEntity;
	}

	@PostMapping("/{busName}/{locnNbr}/ships/warehouse")
	public ResponseEntity createShipForWarehouse(@PathVariable("busName") String busName,
			@PathVariable("locnNbr") Integer locnNbr, @RequestBody ShipCreationRequestDTO shipCreationReq)
			throws IOException {
		long startTime = System.currentTimeMillis();
		log.info("Received Ship Create request for : " + shipCreationReq.toString() + ": at :" + LocalDateTime.now());
		ResponseEntity resEntity = null;
		try {
			resEntity = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
					.body(shipService.createShipForWarehouse(shipCreationReq));
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = ResponseEntity.badRequest()
					.body(new ShipCreationFailedEvent(shipCreationReq, ShippingConstants.SHIPPING_SERVICE_NAME,
							"Error Occured while processing shipping Create request:" + e.getMessage()));
		}
		long endTime = System.currentTimeMillis();
		log.info("Completed Ship Create request for : " + shipCreationReq.toString() + ": at :" + LocalDateTime.now()
				+ " : total time:" + (endTime - startTime) / 1000.00 + " secs");
		return resEntity;
	}
	
	@PostMapping("/{busName}/{locnNbr}/ships/search")
	public ResponseEntity searchshipping(@PathVariable("busName") String busName, @PathVariable("locnNbr") Integer locnNbr,
			@RequestBody ShipSearchRequestDTO shipSearchReq) throws IOException {
		long startTime = System.currentTimeMillis();
		log.info("Received shipping search request for : " + shipSearchReq.toString() + ": at :" + LocalDateTime.now());
		ResponseEntity resEntity = null;
		try {
			resEntity = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
					.body(shipService.searchShipping(shipSearchReq));
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = ResponseEntity.badRequest()
					.body(new ErrorResourceDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
							"Error occured while searching for shipping:" + e.getMessage(), shipSearchReq));
		}
		long endTime = System.currentTimeMillis();
		log.info("Completed shipping search request for : " + shipSearchReq.toString() + ": at :" + LocalDateTime.now()
				+ " : total time:" + (endTime - startTime) / 1000.00 + " secs");
		return resEntity;
	}
	

}
