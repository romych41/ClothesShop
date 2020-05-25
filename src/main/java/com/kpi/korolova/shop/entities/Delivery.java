package com.kpi.korolova.shop.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "delivery_options", schema = "public")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return id == delivery.id &&
                Objects.equals(description, delivery.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
