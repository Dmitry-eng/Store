package com.shop.repository;

import com.shop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT product FROM  PRODUCT product where (product.name like %:value%) " +
            "or (product.description like %:value%) or (product.author like %:value%) or (product.date like %:value%)")
    List<Product> search(String value);
}
