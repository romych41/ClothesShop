package com.kpi.korolova.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kpi.korolova.shop.model.Category;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product_names", schema = "public")
public class ProductName {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private String name;

    @Column
    private String color;

    @Column
    private Boolean fitting = true;

    @Column
    @JsonIgnore
    private String photo;

    @Column(name = "ph_format")
    @JsonIgnore
    private String phFormat;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "productName")
    private Set<ProductModel> productModels = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<ProductModel> getProductModels() {
        return productModels;
    }

    public boolean isFitting() {
        return fitting;
    }

    public void setFitting(boolean fitting) {
        this.fitting = fitting;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhFormat() {
        return phFormat;
    }

    public void setPhFormat(String phFormat) {
        this.phFormat = phFormat;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setProductModels(Set<ProductModel> productModels) {
        this.productModels = productModels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductName that = (ProductName) o;
        return fitting == that.fitting &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(color, that.color) &&
                Objects.equals(photo, that.photo) &&
                Objects.equals(phFormat, that.phFormat) &&
                category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color, fitting, photo, phFormat, category);
    }

    @Override
    public String toString() {
        return "ProductName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", fitting=" + fitting +
                ", photo='" + photo + '\'' +
                ", phFormat='" + phFormat + '\'' +
                ", category=" + category +
                '}';
    }
}
