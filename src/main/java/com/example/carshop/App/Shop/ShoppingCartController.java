package com.example.carshop.App.Shop;



import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

        return shoppingCartService.sum(email);


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
    @DeleteMapping("/{serialNumber}")
    ResponseEntity<?>deleteFromBasket(@PathVariable String serialNumber){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        shoppingCartService.deletePartFromBasket(email,serialNumber);


        return     ResponseEntity.noContent().build();

    }




}
