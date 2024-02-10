package com.pluralsight.springcloudkubernetes.OrderService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final List<Order> orders = new CopyOnWriteArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @PostMapping
    @ResponseBody
    public Order createOrder(@RequestBody Order order) {
        order.setId(counter.incrementAndGet());
        orders.add(order);
        return order;
    }

    @GetMapping
    @ResponseBody
    public List<Order> getAllOrders() {
        return orders;
    }

    @GetMapping("/view")
    public String viewOrders(Model model) {
        model.addAttribute("orders", orders);
        return "viewOrders";
    }
}