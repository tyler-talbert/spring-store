package com.store.ecommerce;

import com.store.ecommerce.entities.Address;
import com.store.ecommerce.entities.Tag;
import com.store.ecommerce.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {

       // ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

        var user = User.builder()
                .name("John")
                .password("123")
                .email("john@gmail.com")
                .build();

        user.addTag("tag1");
        System.out.println(user);
    }

}
