package com.example.carshop.App.Shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class PaymentController {
    @GetMapping("/payment")
    String pay(){
        return "/pay.html";
    }

}
