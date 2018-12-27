package com.threedsoft.shipping.service.shipengine.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Shipment {
	String carrier_id;
	String service_code;
	@Getter
	String validate_address;
	Address ship_to;
	Address ship_from;
	List<Package> packages = new ArrayList();
	RateOptions rate_options;
	
	public void addPackageWeight(Weight weight) {
		Package pkg = new Package();
		pkg.setWeight(weight);
		packages.add(pkg);
	}
	
	public void setIsValidateAddress(boolean validate) {
		if(validate)
			validate_address="validate_only"; //validate_and_clean -- validate and update address based on recommendations
		else
			validate_address="no_validation";
	}
}
