package com.kpi.korolova.shop.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ordered_products", schema = "public")
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "order_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProductModel product;

    @Column
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedProduct that = (OrderedProduct) o;
        return id == that.id &&
                productId == that.productId &&
                amount == that.amount &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, product, amount);
    }

    @Override
    public String toString() {
        return "OrderedProduct{" +
                "id=" + id +
                ", productId=" + productId +
                ", product=" + product +
                ", amount=" + amount +
                '}';
    }
}
