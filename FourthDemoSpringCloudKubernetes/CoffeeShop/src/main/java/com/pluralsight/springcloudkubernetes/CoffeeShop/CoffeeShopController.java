package com.pluralsight.springcloudkubernetes.CoffeeShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("orderDetails")
public class CoffeeShopController {

    private final RestTemplate restTemplate;

    @Autowired
    public CoffeeShopController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @GetMapping("/")
    public String showOrderForm() {
        return "orderForm";
    }

    @PostMapping("/order")
    public String placeOrder(@RequestParam String coffeeType, @RequestParam int quantity,
                             RedirectAttributes redirectAttributes) {
        Order order = new Order(null, coffeeType, quantity);
        Order response = restTemplate.postForObject("http://order-service/orders",
                order, Order.class);

        String orderDetails = String.format("You've ordered %d cup(s) of %s. Order ID: %d",
                response.getQuantity(), response.getCoffeeType(), response.getId());
        redirectAttributes.addFlashAttribute("orderDetails", orderDetails);

        return "redirect:/orderConfirmation";
    }

    @GetMapping("/orderConfirmation")
    public String showOrderConfirmation(SessionStatus status) {
        // After showing the confirmation, we can clear the session.
        status.setComplete();
        return "orderConfirmation";
    }
}
