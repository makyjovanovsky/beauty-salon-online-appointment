package com.example.salonbella.controller.register;

import com.example.salonbella.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping
    public String registerRequest(RegisterRequest registerRequest, Model model) {
        if (userService.checkEmailExists(registerRequest.getEmail())) {
            model.addAttribute("email", "Email already exists");
            return "register";
        }

        if (userService.checkUsernameExists(registerRequest.getUsername())) {
            model.addAttribute("username", "Username already exists");
            return "register";
        }
        userService.registerNewUser(registerRequest);
        return "redirect:/login";
    }
}
