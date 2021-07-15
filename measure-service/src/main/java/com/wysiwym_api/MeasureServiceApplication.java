package com.wysiwym_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
public class MeasureServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeasureServiceApplication.class, args);
	}

}
