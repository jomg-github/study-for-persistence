package com.example.demo.business;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface SpringDataJdbcRepository extends CrudRepository<ProductEntity, Long> {
}
