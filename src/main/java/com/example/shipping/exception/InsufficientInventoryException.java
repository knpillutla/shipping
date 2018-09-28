package com.example.shipping.exception;

public class InsufficientInventoryException extends Exception{

	public InsufficientInventoryException(String msg) {
		super(msg);
	}
}
