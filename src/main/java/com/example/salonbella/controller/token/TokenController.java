package com.example.salonbella.controller.token;

import com.example.salonbella.service.mail.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TokenController {

    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public TokenController(ConfirmationTokenService confirmationTokenService) {
        this.confirmationTokenService = confirmationTokenService;
    }

    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") String token) throws Exception {
        confirmationTokenService.confirmToken(token);
        return "redirect:/login";
    }
}
