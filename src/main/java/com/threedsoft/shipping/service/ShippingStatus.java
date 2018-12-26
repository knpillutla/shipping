package com.threedsoft.shipping.service;

public enum ShippingStatus {
	CREATED(100), ROUTING_COMPLETED(125), SHIPPED(160), CANCELLED(199);
	ShippingStatus(Integer statCode) {
		this.statCode = statCode;
	}

	private Integer statCode;

	public Integer getStatCode() {
		return statCode;
	}
}
