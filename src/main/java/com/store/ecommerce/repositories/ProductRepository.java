package com.store.ecommerce.repositories;

import com.store.ecommerce.dtos.ProductSummary;
import com.store.ecommerce.dtos.ProductSummaryDTO;
import com.store.ecommerce.entities.Category;
import com.store.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCriteriaRepository, JpaSpecificationExecutor<Product> {

    List<Product> findByName(String name);

    List<Product> findByNameLike(String name);

    List<Product> findByNameContains(String name);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    @Procedure("findProductsByPrice")
    List<Product> findProductsByPrice(BigDecimal min, BigDecimal max);

    @Query(value = "select count(*) from products p where p.price between :min and :max", nativeQuery = true)
    long countProductsByPrice(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying
    @Query(value = "update products p set p.price = :newPrice where p.category_id = :category", nativeQuery = true)
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);

    List<ProductSummaryDTO> findByCategory(Category category);
}
