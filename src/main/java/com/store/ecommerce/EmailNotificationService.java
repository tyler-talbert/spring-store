package com.store.ecommerce;

import org.springframework.stereotype.Service;

@Service("email")
public class EmailNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("Email Notification Service: " + message);
    }

}
