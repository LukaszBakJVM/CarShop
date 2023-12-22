package com.example.carshop.App.Shop;


import com.example.carshop.App.LoginAndRegistration.Person;
import com.example.carshop.App.LoginAndRegistration.PersonRepository;

import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasket;
import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasketMapper;
import com.example.carshop.App.Shop.Basket.MotoParts.MotoPartsBasketMapper;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShoppingCartMapper {


private final PersonRepository personRepository;
private final CarPartsBasketMapper carPartsBasketMapper;
private final MotoPartsBasketMapper motoPartsBasketMapper;
private  final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartMapper(PersonRepository personRepository, CarPartsBasketMapper carPartsBasketMapper,
                              MotoPartsBasketMapper motoPartsBasketMapper, ShoppingCartRepository shoppingCartRepository) {
        this.personRepository = personRepository;
        this.carPartsBasketMapper = carPartsBasketMapper;
        this.motoPartsBasketMapper = motoPartsBasketMapper;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    ShoppingCart map(ShoppingCartDto dto){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(dto.getBasketId());

        Person person = personRepository.findByEmail(dto.getPersonEmail()).orElseThrow();
        shoppingCart.setPerson(person);
        ShoppingCart shoppingCart1 = shoppingCartRepository.findByPersonEmail(person.getEmail()).orElseThrow();
        Set<CarPartsBasket> collect = dto.getCarDto().stream().map(carPartsBasketMapper::map).peek(System.out::println).collect(Collectors.toSet());
          shoppingCart.setCarsParts(collect);
        shoppingCart.setMotoParts(shoppingCart1.getMotoParts());





        return shoppingCart;

    }
    ShoppingCartDto map(ShoppingCart shoppingCart){
        ShoppingCartDto dto = new ShoppingCartDto();
        dto.setBasketId(shoppingCart.getId());

        dto.setPersonEmail(shoppingCart.getPerson().getEmail());

      //  Set<CarDto> carDto = shoppingCart.getCarsParts().stream().map(carMapper::map).collect(Collectors.toSet());
      //  dto.setCarDto(carDto);

     //   Set<MotoDto> motoDto = shoppingCart.getMotoParts().stream().map(motoMapper::map).collect(Collectors.toSet());
      //  dto.setMotoDto(motoDto);

        return dto;


    }



}
