package com.store.ecommerce;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.store.ecommerce.services.UserService;



@SpringBootApplication()
public class StoreApplication {


    public static void main(String[] args) {

       ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

       var service = context.getBean(UserService.class);
       service.fetchProducts();
    }

}
