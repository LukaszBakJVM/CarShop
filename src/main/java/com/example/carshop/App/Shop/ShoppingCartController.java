package com.example.carshop.App.Shop;


import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasketDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping("/basket")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

  private final   BigDecimal bigDecimal;

    public ShoppingCartController(ShoppingCartService shoppingCartService, BigDecimal bigDecimal) {
        this.shoppingCartService = shoppingCartService;
        this.bigDecimal = bigDecimal;
    }
    @GetMapping
   ResponseEntity<ShoppingCartDto> findById(@RequestParam long id){
        return ResponseEntity.ok(shoppingCartService.findById(id));
    }
    @GetMapping("/sum")
    BigDecimal sumToPay(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();



        String email = authentication.getName();
        ShoppingCartDto basketByPersonEmail = shoppingCartService.findBasketByPersonEmail(email);
        Set<CarPartsBasketDto> carDto = basketByPersonEmail.getCarDto();
      return   carDto.stream().map(basketSum->basketSum.getPrice()
                .multiply(BigDecimal.valueOf(basketSum.getQuantity())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);



    }
    @GetMapping("/email")
    ResponseEntity<ShoppingCartDto>allBasket(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
      return ResponseEntity.ok(  shoppingCartService.findBasketByPersonEmail(email));
    }


}
