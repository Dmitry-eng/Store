package com.shop.service.product;

import com.shop.model.entity.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.file.File;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private File file;
    @Override
    public boolean save(Product product){
        file.save(product);
        productRepository.save(product);
        return false;

    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
