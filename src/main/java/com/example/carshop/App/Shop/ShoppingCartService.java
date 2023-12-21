package com.example.carshop.App.Shop;


import com.example.carshop.App.Car.Car;
import com.example.carshop.App.Car.CarDto;
import com.example.carshop.App.Car.CarMapper;
import org.springframework.stereotype.Service;



import java.util.Set;
import java.util.stream.Collectors;


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

    public void sell(Set<CarDto>sell, ShoppingCartDto dto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByPersonEmail(dto.getPersonEmail()).orElseThrow();


        Set<Car> collect = sell.stream().map(carMapper::map).collect(Collectors.toSet());
        Set<Car> carsParts = shoppingCart.getCarsParts();
        carsParts.addAll(collect);
        ShoppingCart save = shoppingCartRepository.save(shoppingCart);


        shoppingCartMapper.map(save);

    }










    }


