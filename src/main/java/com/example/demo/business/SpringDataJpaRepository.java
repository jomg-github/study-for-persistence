package com.example.demo.business;

import com.example.demo.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaRepository extends JpaRepository<ProductEntity, Long> {
}
