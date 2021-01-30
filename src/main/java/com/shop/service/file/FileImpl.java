package com.shop.service.file;

import com.shop.model.entity.Product;;
import com.shop.repository.ProductRepository;
import com.shop.service.app.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileImpl implements File {
    @Autowired
    private ProductRepository productRepository;
    private String directory = null;
    public FileImpl(){
        if(directory == null){
            StringBuilder stringBuilder = new StringBuilder(getClass().getResource(Constant.STATIC).getPath().replace("/", "\\"));
            stringBuilder.append("\\image\\");
            directory = stringBuilder.toString();
        }
    }

    public boolean save(Product product) {
        if (product.getDataPhoto().isEmpty()) {
            return false;
        }
        try {
            String address = saveLoadFiles(product.getDataPhoto());
            product.setPhoto(address);
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String saveLoadFiles(MultipartFile file) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        byte[] bytes = file.getBytes();
        java.io.File dir = new java.io.File(directory);
        dir.mkdirs();
        Path path = Paths.get(dir.getPath(), fileName);
        Files.write(path, bytes);
        return Constant.IMAGE + fileName;
    }
}