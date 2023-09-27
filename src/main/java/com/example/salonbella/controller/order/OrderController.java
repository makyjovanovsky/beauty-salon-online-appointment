package com.example.salonbella.controller.order;

import com.example.salonbella.service.ProductService;
import com.example.salonbella.service.ShoppingCartService;
import com.example.salonbella.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ShoppingCartService shoppingCartService, ProductService productService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }



    @PostMapping("/order")
    public String order(@RequestParam Map<String, String> allParams) {
        orderService.changeQuantities(allParams, shoppingCartService.getShoppingCart());
        orderService.saveOrder(shoppingCartService.getShoppingCart());
        orderService.clearCart(shoppingCartService.getShoppingCart());
        return "redirect:/userDashboard";
    }

    @GetMapping("/my-orders")
    public String getMyOrders(Model model) {
        model.addAttribute("orders", orderService.getUserOrders());
        return "user-my-orders";
    }

    @PostMapping("/cancel-order")
    public String cancelOrder(@RequestParam(name = "id") String id) {
        orderService.cancelOrder(Long.parseLong(id));
        return "redirect:/my-orders";
    }

    @GetMapping("/admin-get-orders")
    public String getOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin-orders";
    }

    @PostMapping("/admin-change-status")
    public String changeOrderStatus(@RequestParam(name = "id") String id) {
        orderService.changeOrderStatus(Long.parseLong(id));
        return "redirect:/admin-get-orders";
    }

    @PostMapping("/admin-cancel-order")
    public String cancelOrderAdmin(@RequestParam(name = "id") String id) {
        orderService.cancelOrderAdmin(Long.parseLong(id));
        return "redirect:/admin-get-orders";
    }

    @GetMapping("/order")
    public String getOrderPage(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "user-order";
    }
}

