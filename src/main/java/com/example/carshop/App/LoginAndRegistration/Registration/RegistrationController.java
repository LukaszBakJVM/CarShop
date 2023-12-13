package com.example.carshop.App.LoginAndRegistration.Registration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    private final RegistrationService service;

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    String register(RegistrationDto dto) {
        service.register(dto);
        return "redirect:/index";
    }
}
