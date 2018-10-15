package com.threedsoft.shipping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.threedsoft.shipping.streams.ShippingStreams;
import com.threedsoft.util.service.EventPublisher;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableBinding(ShippingStreams.class)
@EnableAutoConfiguration
@EnableScheduling
@EnableJpaAuditing
@Slf4j
public class ShippingApplication {
	@Autowired
	ShippingStreams shippingStreams;
	
	public static void main(String[] args) {
		SpringApplication.run(ShippingApplication.class, args);
	}
	@Bean
	public EventPublisher eventPublisher() {
		return new EventPublisher(shippingStreams.outboundShip());
	}	
}