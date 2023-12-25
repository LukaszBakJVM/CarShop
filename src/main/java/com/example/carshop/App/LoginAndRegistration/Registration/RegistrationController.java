package com.example.carshop.App.LoginAndRegistration.Registration;


import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class RegistrationController {
    private final RegistrationService service;

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    void register(RegistrationDto dto, HttpServletResponse response) throws IOException {
       service.register(dto);

        response.sendRedirect("/login");
    }
    @DeleteMapping("/persondelete")
    ResponseEntity<?>delete(@RequestParam String email){
        service.delete(email);
        return ResponseEntity.noContent().build();
    }

}
