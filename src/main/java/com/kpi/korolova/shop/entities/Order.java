package com.kpi.korolova.shop.entities;

import com.kpi.korolova.shop.model.OrderStatus;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int number;

    @Column(name = "customer_id")
    private int customerId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer customer;

    @Column
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Column(name = "delivery_id")
    private int deliveryId;

    @ManyToOne
    @JoinColumn(name = "delivery_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Delivery delivery;

    @Column
    private String address;

    @Column
    private Date orderDate;

    @Column
    private boolean freeShipping;

    @OneToMany
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderedProduct> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public List<OrderedProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderedProduct> products) {
        this.products = products;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                number == order.number &&
                customerId == order.customerId &&
                deliveryId == order.deliveryId &&
                freeShipping == order.freeShipping &&
                Objects.equals(customer, order.customer) &&
                status == order.status &&
                Objects.equals(delivery, order.delivery) &&
                Objects.equals(address, order.address) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, customerId, customer, status, deliveryId, delivery, address, orderDate, freeShipping, products);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number=" + number +
                ", customerId=" + customerId +
                ", customer=" + customer +
                ", status=" + status +
                ", deliveryId=" + deliveryId +
                ", delivery=" + delivery +
                ", address='" + address + '\'' +
                ", orderDate=" + orderDate +
                ", freeShipping=" + freeShipping +
                ", products=" + products +
                '}';
    }
}
