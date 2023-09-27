package com.example.salonbella.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CartDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long product_id;
    private int quantity;

    public CartDetailEntity(long product_id, int quantity) {
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public CartDetailEntity() {

    }

    public long getId() {
        return product_id;
    }

    public void setId(long product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartDetail{" +
                "product_id=" + product_id +
                ", quantity=" + quantity +
                '}';
    }
}

