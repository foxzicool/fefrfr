package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private UserOrder userOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductData product;

    private int quantity;
    private double unitPrice;

    // Getters, Setters, and other methods...
}
