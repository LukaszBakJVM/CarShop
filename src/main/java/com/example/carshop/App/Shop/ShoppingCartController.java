package com.example.carshop.App.Shop;


import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasketDto;
import com.example.carshop.App.Shop.Basket.MotoParts.MotoPartsBasketDto;
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



    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;

    }
    @GetMapping("/id")
   ResponseEntity<ShoppingCartDto> findById(@RequestParam long id){
        return ResponseEntity.ok(shoppingCartService.findById(id));
    }
    @GetMapping("/sum")
    BigDecimal sumToPay(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();



        String email = authentication.getName();
        ShoppingCartDto basketByPersonEmail = shoppingCartService.findBasketByPersonEmail(email);
        Set<CarPartsBasketDto> carDto = basketByPersonEmail.getCarDto();
        BigDecimal carPartsSum = carDto.stream().map(basketSum -> basketSum.getPrice()
                        .multiply(BigDecimal.valueOf(basketSum.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Set<MotoPartsBasketDto> motoDto = basketByPersonEmail.getMotoDto();
        BigDecimal motoPartsSum = motoDto.stream().map(basketSum -> basketSum.getPrice()
                        .multiply(BigDecimal.valueOf(basketSum.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return carPartsSum.add(motoPartsSum);


    }
    @GetMapping("/email")
    ResponseEntity<ShoppingCartDto>allBasket(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
      return ResponseEntity.ok(  shoppingCartService.findBasketByPersonEmail(email));
    }

    @GetMapping
    ResponseEntity<Set<ShoppingCartDto>>findAll(@RequestParam(defaultValue = "0") int page){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
      return ResponseEntity.ok(shoppingCartService.findAll(email,page));
    }




}
