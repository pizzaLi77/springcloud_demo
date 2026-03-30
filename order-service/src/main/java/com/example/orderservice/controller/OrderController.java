package com.example.orderservice.controller;

import com.example.userservice.api.feign.UserClient;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderWithUser;
import com.example.userservice.api.entity.UserDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private UserClient userClient;

    @GetMapping("/{id}")
    //@HystrixCommand(fallbackMethod = "getOrderFallback")
    public Order getOrder(@PathVariable Long id) {
        return new Order(id, "ORDER_" + id, 1L, 100.0);
    }

    public Order getOrderFallback(Long id) {
        return new Order(id, "DEFAULT_ORDER", 0L, 0.0);
    }

    @GetMapping("/{id}/with-user")
    public OrderWithUser getOrderWithUser(@PathVariable Long id) {
        Order order = new Order(id, "ORDER_" + id, 1L, 100.0);
        System.out.println(new Date());
        UserDTO user = userClient.getUserById(order.getUserId());
        System.out.println(new Date());
        return new OrderWithUser(order, user);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1L, "ORDER_001", 1L, 199.9));
        orders.add(new Order(2L, "ORDER_002", 2L, 299.9));
        orders.add(new Order(3L, "ORDER_003", 1L, 99.9));
        return orders;
    }
}