package com.example.shipping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.example.shipping.dto.events.BaseEvent;
import com.example.shipping.streams.ShippingStreams;

import lombok.extern.slf4j.Slf4j;

@Service
@EnableBinding(ShippingStreams.class)
@Slf4j
public class EventPublisher {
	@Autowired
	private final ShippingStreams orderStreams;

	public EventPublisher(ShippingStreams orderStreams) {
	        this.orderStreams = orderStreams;
	    }

	public void publish(BaseEvent event) {
		log.info("Sending event {}", event);
		MessageChannel messageChannel = orderStreams.outboundOrders();
		MessageHeaderAccessor msgHdrAccessor = new MessageHeaderAccessor();
		msgHdrAccessor.copyHeadersIfAbsent(event.getHeaderMap());
		messageChannel.send(MessageBuilder.withPayload(event)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
				.setHeaders(msgHdrAccessor)
				.build());
	}
}
