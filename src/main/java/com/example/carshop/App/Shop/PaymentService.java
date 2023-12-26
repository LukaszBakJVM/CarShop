package com.example.carshop.App.Shop;

import com.example.carshop.App.Car.CarService;
import com.example.carshop.App.Moto.MotoService;
import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasketDto;
import com.example.carshop.App.Shop.Basket.MotoParts.MotoPartsBasketDto;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private final CarService carService;
    private final MotoService motoService;
    private final ShoppingCartService shoppingCartService;

    public PaymentService(CarService carService, MotoService motoService, ShoppingCartService shoppingCartService) {
        this.carService = carService;
        this.motoService = motoService;
        this.shoppingCartService = shoppingCartService;
    }
    void findByEmail(String email){
        ShoppingCartDto basketByPersonEmail = shoppingCartService.findBasketByPersonEmail(email);
        Set<CarPartsBasketDto> carDto = basketByPersonEmail.getCarDto();
        Map<String, Integer> carMap = carDto.stream().collect(Collectors
                .toMap(CarPartsBasketDto::getSerialNumber, CarPartsBasketDto::getQuantity));

        Set<MotoPartsBasketDto> motoDto = basketByPersonEmail.getMotoDto();
        Map<String, Integer> motoMap = motoDto.stream().collect(Collectors.toMap(MotoPartsBasketDto::getSerialNumber, MotoPartsBasketDto::getQuantity));
    }
}
