package com.example.demo.business;

import com.example.demo.domain.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class JdbcDemoRepositoryTest {
    private DemoRepository demoRepository;

    @BeforeEach
    void setUp() {
        demoRepository = new JdbcRepository();

    }

    @AfterEach
    void rollback() {
        demoRepository.deleteAll();
    }

    @Test
    void 저장() throws SQLException {
        // given
        Product save = demoRepository.save(new Product("상품1", 1000));

        // when
        Product byId = demoRepository.findById(save.getId());

        // then
        assertThat(byId.getId()).isEqualTo(save.getId());
    }

    @Test
    void 전체검색() throws SQLException {
        // given
        int before = demoRepository.findAll().size();
        int add = 3;

        for (int i = 0 ; i < add; i++) {
            demoRepository.save(new Product("name", 1000));
        }

        // when
        int size = demoRepository.findAll().size();

        // then
        assertThat(size - add).isEqualTo(before);
    }


}