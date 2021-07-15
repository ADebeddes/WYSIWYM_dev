package com.wysiwym_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.wysiwym_api")
public class WysiwymApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WysiwymApiApplication.class, args);
	}

}
