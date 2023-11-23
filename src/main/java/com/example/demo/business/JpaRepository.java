package com.example.demo.business;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Repository
public class JpaRepository implements DemoRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = new ProductEntity(product.getId(), product.getName(), product.getPrice());

        if (product.getId() == null) {
            em.persist(productEntity);
        } else {
            em.merge(productEntity);
        }

        return new Product(productEntity.getId(), productEntity.getName(), productEntity.getPrice());
    }

    @Override
    public Product findById(Long productId) {
        ProductEntity productEntity = em.find(ProductEntity.class, productId);
        return new Product(productEntity.getId(), productEntity.getName(), productEntity.getPrice());
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> productEntities = em.createQuery("select p from ProductEntity p", ProductEntity.class).getResultList();

        return productEntities.stream()
                .map(productEntity -> new Product(productEntity.getId(), productEntity.getName(), productEntity.getPrice()))
                .collect(Collectors.toList());
    }

}
