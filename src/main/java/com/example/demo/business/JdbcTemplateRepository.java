package com.example.demo.business;

import com.example.demo.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcTemplateRepository implements DemoRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product save(Product product) {
//        String sql = "INSERT INTO PRODUCT (name, price) VALUES (?, ?)";
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update(con -> {
//            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, product.getName());
//            preparedStatement.setInt(2, product.getPrice());
//            return preparedStatement;
//        }, keyHolder);
//        Long id = Objects.requireNonNull(keyHolder.getKey()).longValue();

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("PRODUCT").usingGeneratedKeyColumns("id");
        Number key = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(product));
        Long id = key.longValue();

        return findById(id);
    }

    @Override
    public Product findById(Long productId) {
        String sql = "SELECT * FROM PRODUCT WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, productRowMapper(), productId);
    }

    private RowMapper<Product> productRowMapper() {
        return ((rs, rowNum) -> {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");

            return new Product(id, name, price);
        });
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM PRODUCT";
        return jdbcTemplate.query(sql, productRowMapper());
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM PRODUCT";
        jdbcTemplate.update(sql);
    }
}
