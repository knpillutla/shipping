package com.threedsoft.shipping.service.shipengine.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Shipment {
	String service_code;
	Address ship_to;
	Address ship_from;
	List<Package> packages = new ArrayList();
	
	public void addPackageWeight(Weight weight) {
		Package pkg = new Package();
		pkg.setWeight(weight);
		packages.add(pkg);
	}
}
