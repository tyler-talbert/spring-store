package com.store.ecommerce.repositories;

import com.store.ecommerce.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByName(String name);

    List<Product> findByNameLike(String name);

    List<Product> findByNameContains(String name);

    List<Product> findByPrice(BigDecimal price);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);


}
