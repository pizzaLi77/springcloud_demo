package com.example.orderservice.entity;

public class Order {

    private Long id;
    private String orderNo;
    private Long userId;
    private Double amount;

    public Order() {
    }

    public Order(Long id, String orderNo, Long userId, Double amount) {
        this.id = id;
        this.orderNo = orderNo;
        this.userId = userId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}