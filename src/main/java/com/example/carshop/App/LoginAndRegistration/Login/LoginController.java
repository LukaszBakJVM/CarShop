package com.example.carshop.App.LoginAndRegistration.Login;





import com.example.carshop.App.Shop.ShoppingCartDto;
import com.example.carshop.App.Shop.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {
    private final ShoppingCartService shoppingCartService;

    public LoginController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }


    @PostMapping("/login")
    String loginForm(Authentication authentication, Model model){
        String name = authentication.getName();
        System.out.println(name);
        ShoppingCartDto basket = shoppingCartService.findBasketByPersonEmail(name);
        System.out.println(name+"   "+basket.getBasketId());

        model.addAttribute("basket",basket);
        return "/";
        }

        @GetMapping("/login")
    String login(){
        return "login.html";
        }
}
