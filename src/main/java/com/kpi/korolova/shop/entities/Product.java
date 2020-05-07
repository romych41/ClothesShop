package com.kpi.korolova.shop.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "products", schema = "public",
        uniqueConstraints = @UniqueConstraint(columnNames = {"code"}))
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private Long code;

    @Column
    private String name;

    @Column
    private BigDecimal cost;

    @Column
    private BigDecimal markup;

    @Column
    private BigDecimal price;

    @Column
    private Integer amount;

    @Column(name = "add_date")
    private Date addDate;

    @Column
    private BigDecimal storage;

    @Column(name = "amount_left")
    private Integer left;

    @Column(name = "s_cost")
    private BigDecimal sCost;

    @Column(name = "s_price")
    private BigDecimal sPrice;

    @Column
    private String color;

    @Column
    @Enumerated(EnumType.STRING)
    private Size size;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "color_hex")
    private String colorHex;

    @Column(name = "photo")
    private String pathToPhoto;

    public enum Category {
        PANTS("Брюки"),
        UNDEFINED("")
        ;
        private String desc;
        Category(String desc) {
            this.desc = desc;
        }
        public String getDesc() {
            return desc;
        }
        public static Category fromProductName(String desc){
            for(Category c : Category.values()) {
                if(desc.contains(c.desc)) {
                    return c;
                }
            }
            return UNDEFINED;
        }
    }

    public enum Size {
        XXS, XS, S, M, L, XL, XXL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getMarkup() {
        return markup;
    }

    public void setMarkup(BigDecimal markup) {
        this.markup = markup;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public BigDecimal getStorage() {
        return storage;
    }

    public void setStorage(BigDecimal storage) {
        this.storage = storage;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public BigDecimal getsCost() {
        return sCost;
    }

    public void setsCost(BigDecimal sCost) {
        this.sCost = sCost;
    }

    public BigDecimal getsPrice() {
        return sPrice;
    }

    public void setsPrice(BigDecimal sPrice) {
        this.sPrice = sPrice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }

    public void setPathToPhoto(String pathToPhoto) {
        this.pathToPhoto = pathToPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(code, product.code) &&
                Objects.equals(name, product.name) &&
                Objects.equals(cost, product.cost) &&
                Objects.equals(markup, product.markup) &&
                Objects.equals(price, product.price) &&
                Objects.equals(amount, product.amount) &&
                Objects.equals(addDate, product.addDate) &&
                Objects.equals(storage, product.storage) &&
                Objects.equals(left, product.left) &&
                Objects.equals(sCost, product.sCost) &&
                Objects.equals(sPrice, product.sPrice) &&
                Objects.equals(color, product.color) &&
                size == product.size &&
                category == product.category &&
                Objects.equals(colorHex, product.colorHex) &&
                Objects.equals(pathToPhoto, product.pathToPhoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, cost, markup, price, amount, addDate, storage, left, sCost, sPrice, color, size, category, colorHex, pathToPhoto);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", markup=" + markup +
                ", price=" + price +
                ", amount=" + amount +
                ", addDate=" + addDate +
                ", storage=" + storage +
                ", left=" + left +
                ", sCost=" + sCost +
                ", sPrice=" + sPrice +
                ", color='" + color + '\'' +
                ", size=" + size +
                ", category=" + category +
                ", colorHex='" + colorHex + '\'' +
                ", pathToPhoto='" + pathToPhoto + '\'' +
                '}';
    }
}
