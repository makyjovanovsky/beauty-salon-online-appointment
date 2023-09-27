package com.example.salonbella.service;

import com.example.salonbella.controller.cart.ShoppingCart;
import com.example.salonbella.entity.CartDetailEntity;
import com.example.salonbella.entity.ProductEntity;
import com.example.salonbella.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ShoppingCartService {

    private ShoppingCart shoppingCart;
    private ProductRepository productRepository;

    @Autowired
    public ShoppingCartService(ShoppingCart shoppingCart, ProductRepository productRepository) {
        this.shoppingCart = shoppingCart;
        this.productRepository = productRepository;
    }

    public void addProduct(Long id) {
        shoppingCart.getCartDetails().add(new CartDetailEntity(id, 1));
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public List<ProductEntity> getShoppingCartDetails() {
        List<ProductEntity> productEntities = new ArrayList<>();
        List<CartDetailEntity> cartDetailEntities = shoppingCart.getCartDetails();
        for (CartDetailEntity c : cartDetailEntities) {
            ProductEntity p = productRepository.findById(c.getId()).get();
            p.setBase64(Base64.getEncoder().encodeToString(p.getContent()));
            productEntities.add(p);
        }
        return productEntities;
    }

    public void deleteProductFromCart(Long id) {
        for (CartDetailEntity c : shoppingCart.getCartDetails()) {
            if (c.getId() == id) {
                shoppingCart.getCartDetails().remove(c);
                break;
            }
        }
    }

    public boolean existInCart(Long id) {
        for (CartDetailEntity c : shoppingCart.getCartDetails()) {
            if (c.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
