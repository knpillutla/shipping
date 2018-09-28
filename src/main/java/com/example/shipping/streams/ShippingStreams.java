package com.example.shipping.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ShippingStreams {
    public String ORDERS_OUTPUT = "orders-out";
    public String SHIP_OUTPUT="ship-out";
    
    @Input(ORDERS_OUTPUT)
    public SubscribableChannel outboundPick();

    @Output(SHIP_OUTPUT)
    public MessageChannel outboundShip();
}