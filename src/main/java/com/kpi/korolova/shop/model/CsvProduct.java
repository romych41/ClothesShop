package com.kpi.korolova.shop.model;

import com.kpi.korolova.shop.entities.ProductName;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class CsvProduct {
    private Long code;
    private String name;
    private BigDecimal cost;
    private BigDecimal markup;
    private BigDecimal price;
    private BigDecimal amount;
    private Date addDate;
    private BigDecimal storage;
    private BigDecimal left;
    private BigDecimal sCost;
    private BigDecimal sPrice;
    private String color;
    private String size;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public void setAddDate(String addDate) throws ParseException {
        this.addDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(addDate);
    }

    public BigDecimal getStorage() {
        return storage;
    }

    public void setStorage(BigDecimal storage) {
        this.storage = storage;
    }

    public BigDecimal getLeft() {
        return left;
    }

    public void setLeft(BigDecimal left) {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CsvProduct that = (CsvProduct) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(markup, that.markup) &&
                Objects.equals(price, that.price) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(addDate, that.addDate) &&
                Objects.equals(storage, that.storage) &&
                Objects.equals(left, that.left) &&
                Objects.equals(sCost, that.sCost) &&
                Objects.equals(sPrice, that.sPrice) &&
                Objects.equals(color, that.color) &&
                Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, cost, markup, price, amount, addDate, storage, left, sCost, sPrice, color, size);
    }

    @Override
    public String toString() {
        return "CsvProduct{" +
                "code=" + code +
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
                ", size='" + size + '\'' +
                '}';
    }
}
