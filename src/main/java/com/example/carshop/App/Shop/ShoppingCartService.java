package com.example.carshop.App.Shop;



import com.example.carshop.App.Car.CarMapper;
import org.springframework.stereotype.Service;




@Service
public class ShoppingCartService {
    private final  ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CarMapper carMapper;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ShoppingCartMapper shoppingCartMapper, CarMapper carMapper) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartMapper = shoppingCartMapper;

        this.carMapper = carMapper;
    }
 public ShoppingCartDto  findBasketByPersonEmail(String email){
     ShoppingCart shoppingCart = shoppingCartRepository.findByPersonEmail(email).orElseThrow();
   return shoppingCartMapper.map(shoppingCart);
    }

    ShoppingCartDto findById(long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow();
        return shoppingCartMapper.map(shoppingCart);
    }

    public void sell( ShoppingCartDto dto) {
        ShoppingCart map = shoppingCartMapper.map(dto);
         shoppingCartRepository.save(map);
       // return shoppingCartMapper.map(save);
    }


    }













