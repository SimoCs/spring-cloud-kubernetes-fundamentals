package com.pluralsight.springcloudkubernetes.CoffeeShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CoffeeShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeShopApplication.class, args);
	}

}
