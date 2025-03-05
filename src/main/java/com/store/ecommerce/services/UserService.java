package com.store.ecommerce.services;

import com.store.ecommerce.entities.Address;
import com.store.ecommerce.entities.Category;
import com.store.ecommerce.entities.Product;
import com.store.ecommerce.entities.User;
import com.store.ecommerce.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;


    public void showEntityState() {
        var user = User.builder()
                .name("John Doe")
                .email("john.doe@gmail.com")
                .password("123")
                .build();

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        }
        else
            System.out.println("Transient / Detatched");

        userRepository.save(user);

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        }
        else
            System.out.println("Transient / Detatched");
    }

    @Transactional
    public void showRelatedEntities() {
        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }


    public void fetchAddress() {
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println(address.getUser().getAddresses());
    }

    public void persistRelated() {
        var user = User.builder()
                .name("John Smith")
                .email("john.smith@gmail.com")
                .password("123")
                .build();

        var address = Address.builder()
                .street("Street")
                .city("City")
                .state("State")
                .zip("Zip")
                .build();

        user.addAddress(address);

        userRepository.save(user);
    }

    @Transactional
    public void deleteRelated() {
        var user = userRepository.findById(3L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void manageProducts() {
        var product = productRepository.findById(2L).orElseThrow();
        productRepository.deleteById(product.getId());
    }


    public void fetchCategory() {
        var category = categoryRepository.findById((byte) 1).orElse(null);
        System.out.println(category);
    }


}
