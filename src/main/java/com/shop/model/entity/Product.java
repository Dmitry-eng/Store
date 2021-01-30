package com.shop.model.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Objects;

@Table
@Entity(name = "PRODUCT")
public class Product extends StandardEntity {
    @Column(name = "NAME")
    private String name;
    @GeneratedValue
    @Column(name = "code")
    private long code;
    @Column(name = "DESCRIPTION", length = 10000)
    private String description;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "date")
    private String date;
    @Column(name = "photo")
    private String photo;
    @Transient
    private MultipartFile dataPhoto;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public MultipartFile getDataPhoto() {
        return dataPhoto;
    }

    public void setDataPhoto(MultipartFile dataPhoto) {
        this.dataPhoto = dataPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return code == product.code && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(author, product.author) && Objects.equals(date, product.date) && Objects.equals(photo, product.photo) && Objects.equals(dataPhoto, product.dataPhoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, code, description, author, date, photo, dataPhoto);
    }
}