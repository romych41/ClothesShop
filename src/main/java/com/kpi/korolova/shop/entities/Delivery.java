package com.kpi.korolova.shop.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "delivery_options", schema = "public")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String description;

    @Column
    private BigDecimal cost;

    @Column
    private boolean deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return id == delivery.id &&
                deleted == delivery.deleted &&
                Objects.equals(description, delivery.description) &&
                Objects.equals(cost, delivery.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, cost, deleted);
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", deleted=" + deleted +
                '}';
    }
}
