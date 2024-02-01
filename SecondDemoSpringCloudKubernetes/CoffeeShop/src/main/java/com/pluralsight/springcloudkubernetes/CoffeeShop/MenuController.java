package com.pluralsight.springcloudkubernetes.CoffeeShop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {

    // Injecting an external configuration property
    @Value("${coffeeshop.special-offer:No special offers currently}")
    private String specialOffer;

    private static class MenuItem {
        private String name;
        private double price;

        public MenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    private final List<MenuItem> menu = new ArrayList<>();

    public MenuController() {
        // Initialize the menu with some items
        menu.add(new MenuItem("Espresso", 2.50));
        menu.add(new MenuItem("Cappuccino", 3.00));
        menu.add(new MenuItem("Latte", 3.50));
        menu.add(new MenuItem("Muffin", 2.00));
        menu.add(new MenuItem("Croissant", 2.50));
    }

    @GetMapping("/menu")
    public String getMenu(Model model) {
        model.addAttribute("menu", menu);
        model.addAttribute("specialOffer", specialOffer); // Add special offer to the model
        return "menu"; // The name of the HTML file (without .html extension)
    }

    @PostMapping("/order")
    public String placeOrder(@RequestBody List<MenuItem> orderItems, Model model) {
        double total = orderItems.stream().mapToDouble(MenuItem::getPrice).sum();
        String confirmationMessage = "Order placed! Total amount: $" + String.format("%.2f", total);
        model.addAttribute("orderConfirmation", confirmationMessage);
        model.addAttribute("menu", menu); // Re-add the menu items for the view
        return "menu"; // Redirect back to the menu view with confirmation
    }
}