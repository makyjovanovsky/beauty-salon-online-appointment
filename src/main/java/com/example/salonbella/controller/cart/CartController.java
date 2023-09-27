package com.example.salonbella.controller.cart;

import com.example.salonbella.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public CartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/add-to-cart")
    public String addProductToCart(@RequestParam(name = "id") String id) {
        if(!shoppingCartService.existInCart(Long.parseLong(id))) {
            shoppingCartService.addProduct(Long.parseLong(id));
        }
        return "redirect:/order";
    }

    @GetMapping("/my-cart")
    public String getCartDetails(Model model) {
        model.addAttribute("products", shoppingCartService.getShoppingCartDetails());
        return "user-cart";
    }

    @GetMapping("/delete-product-from-cart")
    public String deleteProductFromCart(@RequestParam(name = "id") String id) {
        shoppingCartService.deleteProductFromCart(Long.parseLong(id));
        return "redirect:/my-cart";
    }

}
