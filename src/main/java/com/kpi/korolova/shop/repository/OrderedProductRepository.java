package com.kpi.korolova.shop.repository;

import com.kpi.korolova.shop.entities.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Integer> {
    void removeAllByOrderId(int orderId);
}
