package com.store.ecommerce;

import org.springframework.stereotype.Service;

@Service("stripe")
public class StripePaymentService implements PaymentService {

    @Override
    public void processPayment(double amount) {
        // apiUrl
        // apiKey
        System.out.println("STRIP");
        System.out.println("Amount: " + amount);
    }

}
