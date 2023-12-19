package com.example.carshop.App.Shop;


import org.springframework.stereotype.Service;



@Service
public class ShoppingCartService {
    private final  ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartMapper = shoppingCartMapper;

    }
 public ShoppingCartDto  findBasketByPersonId(String email){
     ShoppingCart shoppingCart = shoppingCartRepository.findByPersonEmail(email).orElseThrow();
   return shoppingCartMapper.map(shoppingCart);
    }
  public   ShoppingCartDto save(ShoppingCartDto dto){
        ShoppingCart map = shoppingCartMapper.map(dto);
        ShoppingCart save = shoppingCartRepository.save(map);
        return shoppingCartMapper.map(save);
    }

}
