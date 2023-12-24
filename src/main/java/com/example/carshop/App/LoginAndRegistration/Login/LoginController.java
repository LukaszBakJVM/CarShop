package com.example.carshop.App.LoginAndRegistration.Login;






import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class LoginController {
    public LoginController() {
    }

    @GetMapping("/login")
    String login(){
        return "login.html";
        }
}
