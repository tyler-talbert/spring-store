package com.store.ecommerce;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import services.UserService;



@SpringBootApplication(scanBasePackages = {"com.store.ecommerce", "services"})
public class StoreApplication {


    public static void main(String[] args) {

       ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

       var user = context.getBean(UserService.class);

       user.manageProducts();
    }

}
