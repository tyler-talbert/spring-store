package com.store.ecommerce.services;

import com.store.ecommerce.entities.Address;
import com.store.ecommerce.entities.Product;
import com.store.ecommerce.entities.User;
import com.store.ecommerce.repositories.*;
import com.store.ecommerce.repositories.specifications.ProductSpec;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
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

    @Transactional
    public void updateProductPrices(int newPrice, int categoryId) {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(newPrice), (byte) categoryId);
    }


    @Transactional
    public void fetchProducts() {
       var product = new Product();
       product.setName("tim");

       var matcher = ExampleMatcher.matching()
               .withIncludeNullValues()
               .withIgnorePaths("id", "description")
               .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

       var example = Example.of(product, matcher);
       var products = productRepository.findAll(example);
       products.forEach(System.out::println);
    }

    public void fetchProductsByCriteria() {
        var products = productRepository.findProductsByCriteria("tim", BigDecimal.valueOf(1), null);
        products.forEach(System.out::println);
    }

    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Product> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }

        productRepository.findAll(spec).forEach(System.out::println);
    }

    @Transactional
    public void fetchUsers() {
        var user = userRepository.findAllWithAddresses();

        user.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public void printLoyalProfiles() {
        var users = userRepository.findLoyalUsers(2);
        users.forEach( p -> System.out.println(p.getId() + ": " + p.getEmail()));
    }


}
