package com.example.demo.business;

import com.example.demo.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JdbcDemoRepositoryTest {
    @Qualifier("jpaRepository") @Autowired
    DemoRepository demoRepository;

//    @BeforeEach
//    void setUp() {
////        demoRepository = new JdbcRepository();
//
////        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);
////        JdbcTemplate jdbcTemplate = ac.getBean(JdbcTemplate.class);
////        demoRepository = new JdbcTemplateRepository(jdbcTemplate);
//
////        demoRepository = new JpaRepository();
//
//    }
//
////    @AfterEach
////    void rollback() {
////        demoRepository.deleteAll();
////    }

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