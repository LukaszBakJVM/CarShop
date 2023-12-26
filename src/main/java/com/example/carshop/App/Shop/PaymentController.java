package com.example.carshop.App.Shop;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class PaymentController {
    private final PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping("/payment")
    String pay() {
        return "/pay.html";
    }

    @GetMapping("/paymentOk")
    String ok(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        paymentService.findByEmail(email);


        return "/";
        }


}
