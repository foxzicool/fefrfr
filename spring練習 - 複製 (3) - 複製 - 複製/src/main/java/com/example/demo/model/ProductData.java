package com.example.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product_data")
public class ProductData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String title;
    private Double origin_price;
    private Double price;
    private Boolean is_enabled;

    @ManyToMany(mappedBy = "products")
    private Set<UserOrder> orders = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getOrigin_price() {
        return origin_price;
    }

    public void setOrigin_price(Double origin_price) {
        this.origin_price = origin_price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getIs_enabled() {
        return is_enabled;
    }

    public void setIs_enabled(Boolean is_enabled) {
        this.is_enabled = is_enabled;
    }

    public Set<UserOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<UserOrder> orders) {
        this.orders = orders;
    }

    // Order relationship methods
    public void addOrder(UserOrder order) {
        this.orders.add(order);
        order.getProducts().add(this);
    }

    public void removeOrder(UserOrder order) {
        this.orders.remove(order);
        order.getProducts().remove(this);
    }
}
