package com.example.salonbella.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String getLoginPage(@RequestParam Map<String, String> allParams, Model model) {
        for (String s : allParams.keySet()) {
            if (s.equals("error")) {
                model.addAttribute("message", "Invalid username/password");
                return "login";
            }
        }
        return "login";
    }

    @GetMapping("/userDashboard")
    public String getUserPage() {
        return "user-page";
    }

    @GetMapping("/adminDashboard")
    public String getAdminPage() {
        return "admin-page";
    }
}
