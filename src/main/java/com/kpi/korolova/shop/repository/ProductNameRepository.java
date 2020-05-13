package com.kpi.korolova.shop.repository;

import com.kpi.korolova.shop.entities.ProductName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductNameRepository extends JpaRepository<ProductName, Integer>, JpaSpecificationExecutor<ProductName> {
    boolean existsByNameAndColor(String name, String color);
    ProductName getByNameAndColor(String name, String color);
}
