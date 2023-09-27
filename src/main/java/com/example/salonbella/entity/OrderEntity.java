package com.example.salonbella.entity;

import com.example.salonbella.service.order.OrderDetail;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartDetailEntity> cartDetailEntities = new ArrayList<>();

    @Column(name = "status", nullable = false, length = 30)
    private String status;

    @Column(name = "date", nullable = false, length = 30)
    private LocalDate localDate;

    @Column(name = "total_price", nullable = false)
    private double total;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Transient
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartDetailEntity> getCartDetails() {
        return cartDetailEntities;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setCartDetails(List<CartDetailEntity> cartDetailEntities) {
        this.cartDetailEntities = cartDetailEntities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

