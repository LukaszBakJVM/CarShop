package com.example.carshop.App.LoginAndRegistration.Login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ChangePassword {
    private final LoginService loginService;

    public ChangePassword(LoginService loginService) {
        this.loginService = loginService;
    }
    @PostMapping("/change-password")
    String change(@RequestParam String newPassword){
        loginService.changePassword(newPassword);
        return "redirect:/";

    }
}
