package com.kpi.korolova.shop;

import com.kpi.korolova.shop.entities.Product;
import com.kpi.korolova.shop.util.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void upsertProducts(List<Product> products) {
        String sql = Dialect.getSQLScript("classpath:/sql/insertProduct.sql");
        List<Object[]> params = new ArrayList<>();
        for (Product product : products) {
            params.add(new Object[]{
                    product.getAddDate(),
                    product.getAmount(),
                    product.getCategory().toString(),
                    product.getCode(),
                    product.getColor(),
                    product.getCost(),
                    product.getLeft(),
                    product.getMarkup(),
                    product.getName(),
                    product.getPrice(),
                    product.getsCost(),
                    product.getsPrice(),
                    product.getSize().toString(),
                    product.getStorage(),
                    product.getAddDate(),
                    product.getAmount(),
                    product.getCategory().toString(),
                    product.getColor(),
                    product.getCost(),
                    product.getLeft(),
                    product.getMarkup(),
                    product.getName(),
                    product.getPrice(),
                    product.getsCost(),
                    product.getsPrice(),
                    product.getSize().toString(),
                    product.getStorage(),
            });
        }
        jdbcTemplate.batchUpdate(sql, params);
    }
}
