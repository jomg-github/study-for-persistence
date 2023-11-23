package com.example.demo.business;

import com.example.demo.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface DemoRepository {
    Product save(Product product) throws SQLException;

    Product findById(Long productId);

    List<Product> findAll();

    default void deleteAll() {};
}
