package com.threedsoft.shipping.service.shipengine.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.threedsoft.shipping.dto.requests.ShipCreationRequestDTO;
import com.threedsoft.shipping.dto.requests.ShipLineCreationRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Address {
	private String name;
	private String phone;
	private String company_name;
	private String address_line1;
	private String address_line2;
	private String city_locality;
	private String state_province;
	private String postal_code;
	private String country_code;
	private String address_residential_indicator;
}
