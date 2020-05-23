package com.kpi.korolova.shop.repository.specifications;

import com.kpi.korolova.shop.entities.ProductName;
import com.kpi.korolova.shop.model.Category;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification implements Specification<ProductName> {
    private final String name;
    private final BigDecimal minPrice;
    private final BigDecimal maxPrice;
    private final String color;
    private final Category category;

    public ProductSpecification(String name, BigDecimal minPrice, BigDecimal maxPrice, String color, Category category) {
        this.name = name;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.color = color;
        this.category = category;
    }

    @Override
    public Predicate toPredicate(Root<ProductName> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(name != null && !name.isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }
        if(minPrice != null){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.join("productModels", JoinType.LEFT).get("price"), minPrice));
        }
        if(maxPrice != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.join("productModels", JoinType.LEFT).get("price"), maxPrice));
        }
        if(color != null) {
            predicates.add(criteriaBuilder.equal(root.get("color"), color));
        }
        if(category != null) {
            predicates.add(criteriaBuilder.equal(root.get("category"), category));
        }
        Predicate[] predicates1 = new Predicate[predicates.size()];
        predicates.toArray(predicates1);
        return criteriaBuilder.and(predicates1);
    }
}
