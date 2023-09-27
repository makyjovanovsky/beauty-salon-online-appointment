package com.example.salonbella.controller.cart;

import com.example.salonbella.entity.CartDetailEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {
    private List<CartDetailEntity> cartDetailEntities = new ArrayList<>();
    public List<CartDetailEntity> getCartDetails() {
        return cartDetailEntities;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartDetails=" + cartDetailEntities +
                '}';
    }
}
