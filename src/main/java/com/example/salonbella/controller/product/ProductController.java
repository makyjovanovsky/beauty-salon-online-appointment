package com.example.salonbella.controller.product;

import com.example.salonbella.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ProductController {

    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;

    }


    @GetMapping("/admin-add-product")
    public String getAddProductPage() {
        return "admin-product";
    }

    @PostMapping("/admin-add-product")
    public String addProduct(@RequestParam(name = "name") String name, @RequestParam(name = "category") String category,
                             @RequestParam(name = "price") String price,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "image") MultipartFile multipartFile) throws IOException {
        productService.addProduct(name, category, price, description, multipartFile);
        return "redirect:/adminDashboard";
    }



    @GetMapping("/admin-remove-product")
    public String getRemoveProductPage(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "admin-remove-product";
    }

    @PostMapping("/admin-remove-product")
    public String removeProduct(@RequestParam(name = "id") String id) {
        productService.removeProduct(Long.parseLong(id));
        return "redirect:/admin-remove-product";
    }


}

