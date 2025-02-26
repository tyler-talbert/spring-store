package com.store.ecommerce;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stripe")
public class StripePaymentService implements PaymentService {

    @Value("${stripe.apiUrl}")
    private String apiUrl;

    @Value("${stripe.enabled}")
    private boolean enabled;

    @Value("${stripe.timeout:3000}") // defines a deault value in case its not defined in application.properties
    private int timeout;

    @Value("${stripe.supported-currencies}")
    private List<String> supportedCurrencies;


    @Override
    public void processPayment(double amount) {
        System.out.println("API: " + apiUrl);
        System.out.println("Enabled: " + enabled);
        System.out.println("Timeout: " + timeout);
        System.out.println("STRIPE");
        System.out.println("Amount: " + amount);
    }

}
