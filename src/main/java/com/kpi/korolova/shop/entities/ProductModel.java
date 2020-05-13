package com.kpi.korolova.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kpi.korolova.shop.model.Size;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products", schema = "public")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Integer id;

    @Column
    @Enumerated(EnumType.STRING)
    private Size size;

    @Column
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "product_name_id", referencedColumnName = "id")
    @JsonIgnore
    private ProductName productName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductName getProductName() {
        return productName;
    }

    public void setProductName(ProductName productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductModel that = (ProductModel) o;
        return Objects.equals(id, that.id) &&
                size == that.size &&
                Objects.equals(price, that.price) &&
                Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, price, productName);
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", size=" + size +
                ", price=" + price +
                ", productName=" + productName +
                '}';
    }
}
