package com.example.carshop.App.Shop;

import com.example.carshop.App.Car.*;
import com.example.carshop.App.Moto.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShoppingCartMapper {

private final CarService carService;
private final MotoService motoService;
private final CarMapper carMapper;
private final MotoMapper motoMapper;

    public ShoppingCartMapper(CarService carService, MotoService motoService, CarMapper carMapper, MotoMapper motoMapper) {
        this.carService = carService;
        this.motoService = motoService;
        this.carMapper = carMapper;
        this.motoMapper = motoMapper;
    }


    ShoppingCart map(ShoppingCartDto dto){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(dto.getBasketId());
        Set<Car> carParts = new HashSet<>(shoppingCart.getCarsParts());
        shoppingCart.setCarsParts(carParts);

        Set<MotoParts> motoParts = new HashSet<>(shoppingCart.getMotoParts());
        shoppingCart.setMotoParts(motoParts);


        return shoppingCart;

    }
    ShoppingCartDto map(ShoppingCart shoppingCart){
        ShoppingCartDto dto = new ShoppingCartDto();
        dto.setBasketId(shoppingCart.getId());

        Set<CarDto> carDto = shoppingCart.getCarsParts().stream().map(carMapper::map).collect(Collectors.toSet());
        dto.setCarDto(carDto);

        Set<MotoDto> motoDto = shoppingCart.getMotoParts().stream().map(motoMapper::map).collect(Collectors.toSet());
        dto.setMotoDto(motoDto);

        return dto;


    }

}
