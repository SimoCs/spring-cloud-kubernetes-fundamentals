package com.pluralsight.CoffeeShop;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MenuEndpointHealthIndicator implements HealthIndicator {

    private final RestTemplate restTemplate;
    private final String menuEndpointUrl = "http://localhost:8080/menu";

    public MenuEndpointHealthIndicator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Health health() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(menuEndpointUrl, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return Health.up().withDetail("Menu Endpoint", "Available").build();
            } else {
                return Health.down().withDetail("Menu Endpoint", "Status code: " + response.getStatusCode()).build();
            }
        } catch (Exception e) {
            return Health.down(e).build();
        }
    }
}
