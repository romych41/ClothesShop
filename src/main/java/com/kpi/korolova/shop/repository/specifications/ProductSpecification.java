package com.kpi.korolova.shop.repository.specifications;

import com.kpi.korolova.shop.entities.ProductName;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.math.BigDecimal;

public class ProductSpecification implements Specification<ProductName> {
    private final String name;
    private final BigDecimal minPrice;
    private final BigDecimal maxPrice;
    private final String color;

    public ProductSpecification(String name, BigDecimal minPrice, BigDecimal maxPrice, String color) {
        this.name = name;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.color = color;
    }

    @Override
    public Predicate toPredicate(Root<ProductName> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if(name != null && !name.isEmpty()) {
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        }
        else if(minPrice != null){
            return criteriaBuilder.greaterThanOrEqualTo(root.join("productModels", JoinType.LEFT).get("price"), minPrice);
        }  else if(maxPrice != null) {
            return criteriaBuilder.lessThanOrEqualTo(root.join("productModels", JoinType.LEFT).get("price"), maxPrice);
        } else if(color != null) {
            return criteriaBuilder.equal(root.get("color"), color);
        }
        return null;
    }
}
