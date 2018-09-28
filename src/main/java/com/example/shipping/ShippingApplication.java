package com.example.shipping;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.shipping.streams.ShippingStreams;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableBinding(ShippingStreams.class)
@EnableAutoConfiguration
@EnableScheduling
@Slf4j
public class ShippingApplication {
	private Random random = new Random();
	public static void main(String[] args) {
		SpringApplication.run(ShippingApplication.class, args);
	}
}
