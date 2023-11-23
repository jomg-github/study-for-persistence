package com.example.demo.business;

import com.example.demo.domain.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest(properties = {
        "spring.datasource.url=jdbc:h2:tcp://localhost/~/workspace/utils/h2/demo",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.datasource.driver-class-name=org.h2.Driver"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SpringDataRepositoryTest {
    @Autowired
    private SpringDataJdbcRepository springDataJdbcRepository;

    @Test
    void 저장() {
        // given
        ProductEntity save = springDataJdbcRepository.save(new ProductEntity("상품1", 1000));

        // when
        ProductEntity byId = springDataJdbcRepository.findById(save.getId()).orElseThrow();

        // then
        assertThat(byId.getId()).isEqualTo(save.getId());
    }

    @Test
    void 전체검색() {
        // given
        long before = springDataJdbcRepository.count();
        int add = 10;

        for (int i = 0 ; i < add; i++) {
            springDataJdbcRepository.save(new ProductEntity("name", 1000));
        }

        // when
        long size = springDataJdbcRepository.count();

        // then
        assertThat(size - add).isEqualTo(before);
    }


}