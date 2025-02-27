package com.store.ecommerce;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
public class OrderService {
    private PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostConstruct
    public void init() {
        System.out.println("Orderservice PostConstruct");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Orderservice PreDestroy");
    }

    public void placeOrder() {
        paymentService.processPayment(10);
    }


}
