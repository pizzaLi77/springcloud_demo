package com.example.orderservice.entity;

import com.example.userservice.api.entity.UserDTO;

public class OrderWithUser {

    private Order order;
    private UserDTO user;

    public OrderWithUser() {
    }

    public OrderWithUser(Order order, UserDTO user) {
        this.order = order;
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}