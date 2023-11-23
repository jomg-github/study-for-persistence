package com.example.demo.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCT")
@org.springframework.data.relational.core.mapping.Table("PRODUCT")
public class ProductEntity {
    @Id @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductEntity(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

}
