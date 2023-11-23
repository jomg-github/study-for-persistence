package com.example.demo.business;

import com.example.demo.domain.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:tcp://localhost/~/workspace/utils/h2/demo",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.datasource.driver-class-name=org.h2.Driver"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SpringDataJpaRepositoryTest {
    @Autowired
    private SpringDataJpaRepository repository;

    @Test
    void 저장() {
        // given
        ProductEntity save = repository.save(new ProductEntity("상품1", 1000));

        // when
        ProductEntity byId = repository.findById(save.getId()).orElseThrow();

        // then
        assertThat(byId.getId()).isEqualTo(save.getId());
    }

    @Test
    void 전체검색() {
        // given
        long before = repository.count();
        int add = 10;

        for (int i = 0 ; i < add; i++) {
            repository.save(new ProductEntity("name", 1000));
        }

        // when
        long size = repository.count();

        // then
        assertThat(size - add).isEqualTo(before);
    }


}