package com.shop.controller;

import com.shop.model.entity.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class RestProduct {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @PutMapping("list")
    public List<Product> list() {
        return productService.findAll();
    }
    @DeleteMapping("remove/{uuid}")
    public boolean delete(@PathVariable String uuid){
        productRepository.deleteById(UUID.fromString(uuid));
        return true;
    }
    @PostMapping("add")
    public boolean add(@ModelAttribute Product product){
        productService.save(product);
        return true;
    }
    @GetMapping("get/{uuid}")
    public Product getProduct(@PathVariable String uuid){
        return productRepository.getOne(UUID.fromString(uuid));
    }
    @PutMapping("list/{value}")
    public List<Product> search(@PathVariable String value){
        return productRepository.search(value);
    }
}
