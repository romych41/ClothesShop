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

    @Column(name = "product_name_id")
    private int productNameId;

    @Column(name = "order_id", insertable = false, updatable = false)
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

    public int getProductNameId() {
        return productNameId;
    }

    public void setProductNameId(int productNameId) {
        this.productNameId = productNameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedProduct that = (OrderedProduct) o;
        return id == that.id &&
                productId == that.productId &&
                productNameId == that.productNameId &&
                orderId == that.orderId &&
                amount == that.amount &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, productNameId, orderId, product, amount);
    }

    @Override
    public String toString() {
        return "OrderedProduct{" +
                "id=" + id +
                ", productId=" + productId +
                ", productNameId=" + productNameId +
                ", orderId=" + orderId +
                ", product=" + product +
                ", amount=" + amount +
                '}';
    }
}
