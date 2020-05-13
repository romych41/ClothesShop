package com.kpi.korolova.shop.repository;

import com.kpi.korolova.shop.model.Size;
import com.kpi.korolova.shop.entities.ProductModel;
import com.kpi.korolova.shop.entities.ProductName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    boolean existsByProductNameAndSize(ProductName productName, Size size);
    ProductModel findByProductNameAndSize(ProductName productName, Size size);
}
