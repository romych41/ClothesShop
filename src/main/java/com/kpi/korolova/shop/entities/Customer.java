package com.kpi.korolova.shop.entities;

import com.kpi.korolova.shop.util.MapConverter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "customers", schema = "public")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "full_name")
    private String fullName;
    @Column
    private String phone;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private User user;

    @Column(name = "info")
    @Convert(converter = MapConverter.class)
    private Map<String, Object> attributes;

    public Customer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(fullName, customer.fullName) &&
                Objects.equals(phone, customer.phone) &&
                Objects.equals(user, customer.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, phone, user);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", user=" + user +
                '}';
    }
}
