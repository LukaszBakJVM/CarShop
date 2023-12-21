package com.example.carshop.App.Shop;

import com.example.carshop.App.Car.*;
import com.example.carshop.App.LoginAndRegistration.Person;
import com.example.carshop.App.LoginAndRegistration.PersonRepository;
import com.example.carshop.App.Moto.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShoppingCartMapper {


private final PersonRepository personRepository;
private final CarMapper carMapper;
private final MotoMapper motoMapper;

    public ShoppingCartMapper(PersonRepository personRepository, CarMapper carMapper, MotoMapper motoMapper) {
        this.personRepository = personRepository;
        this.carMapper = carMapper;
        this.motoMapper = motoMapper;
    }


    ShoppingCart map(ShoppingCartDto dto){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(dto.getBasketId());
        Person person = personRepository.findByEmail(dto.getPersonEmail()).orElseThrow();
        shoppingCart.setPerson(person);

        Set<Car> carParts = shoppingCart.getCarsParts();
        shoppingCart.setCarsParts(carParts);

        Set<MotoParts> motoParts = shoppingCart.getMotoParts();
        shoppingCart.setMotoParts(motoParts);


        return shoppingCart;

    }
    ShoppingCartDto map(ShoppingCart shoppingCart){
        ShoppingCartDto dto = new ShoppingCartDto();
        dto.setBasketId(shoppingCart.getId());
        dto.setPersonEmail(shoppingCart.getPerson().getEmail());

        Set<CarDto> carDto = shoppingCart.getCarsParts().stream().map(carMapper::map).collect(Collectors.toSet());
        dto.setCarDto(carDto);

        Set<MotoDto> motoDto = shoppingCart.getMotoParts().stream().map(motoMapper::map).collect(Collectors.toSet());
        dto.setMotoDto(motoDto);

        return dto;


    }

}
