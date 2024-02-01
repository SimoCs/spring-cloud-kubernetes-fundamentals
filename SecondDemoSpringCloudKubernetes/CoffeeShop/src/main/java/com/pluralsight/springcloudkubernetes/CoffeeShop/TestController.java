package com.pluralsight.springcloudkubernetes.CoffeeShop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${coffeeshop.special-offer:Special offer not available}")
    private String specialOffer;

    @GetMapping("/testSpecialOffer")
    public String showSpecialOffer() {
        return "The special offer is: " + specialOffer;
    }
}
