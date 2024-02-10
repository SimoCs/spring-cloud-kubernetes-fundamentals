package com.pluralsight.springcloudkubernetes.CoffeeShop;

public class Order {
    private Long id;

    private String coffeeType;
    private int quantity;

    // Default constructor is necessary for JSON serialization/deserialization
    public Order() {
    }

    // Constructor with all fields
    public Order(Long id, String coffeeType, int quantity) {
        this.id = id;
        this.coffeeType = coffeeType;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(String coffeeType) {
        this.coffeeType = coffeeType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // toString() method for logging and debugging purposes
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", coffeeType='" + coffeeType + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
