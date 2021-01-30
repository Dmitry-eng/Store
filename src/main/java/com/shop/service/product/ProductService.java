package com.shop.service.product;

import com.shop.model.entity.Product;

import java.util.List;

public interface ProductService {
    boolean save(Product product);
    List<Product> findAll();
}
