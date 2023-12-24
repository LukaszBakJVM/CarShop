package com.example.carshop.App.Shop;



import com.example.carshop.App.LoginAndRegistration.Person;
import com.example.carshop.App.LoginAndRegistration.PersonRepository;


import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasket;
import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasketDto;
import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasketMapper;
import com.example.carshop.App.Shop.Basket.MotoParts.MotoPartsBasket;
import com.example.carshop.App.Shop.Basket.MotoParts.MotoPartsBasketDto;
import com.example.carshop.App.Shop.Basket.MotoParts.MotoPartsBasketMapper;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShoppingCartMapper {


private final PersonRepository personRepository;
private final CarPartsBasketMapper carPartsBasketMapper;
private final MotoPartsBasketMapper motoPartsBasketMapper;


    public ShoppingCartMapper(PersonRepository personRepository, CarPartsBasketMapper carPartsBasketMapper,
                              MotoPartsBasketMapper motoPartsBasketMapper) {
        this.personRepository = personRepository;
        this.carPartsBasketMapper = carPartsBasketMapper;
        this.motoPartsBasketMapper = motoPartsBasketMapper;

    }


    ShoppingCart map(ShoppingCartDto dto){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(dto.getBasketId());

        Person person = personRepository.findByEmail(dto.getPersonEmail()).orElseThrow();
        shoppingCart.setPerson(person);

        Set<CarPartsBasket> carsParts = shoppingCart.getCarsParts();
        shoppingCart.setCarsParts(carsParts);

        Set<MotoPartsBasket> motoParts = shoppingCart.getMotoParts();
        shoppingCart.setMotoParts(motoParts);


        return shoppingCart;

    }
    ShoppingCartDto map(ShoppingCart shoppingCart) {
        ShoppingCartDto dto = new ShoppingCartDto();
        dto.setBasketId(shoppingCart.getId());

        dto.setPersonEmail(shoppingCart.getPerson().getEmail());

        Set<CarPartsBasketDto> collect = shoppingCart.getCarsParts().stream().map(carPartsBasketMapper::map).collect(Collectors.toSet());
        dto.setCarDto(collect);

        Set<MotoPartsBasketDto> collect1 = shoppingCart.getMotoParts().stream().map(motoPartsBasketMapper::map).collect(Collectors.toSet());
        dto.setMotoDto(collect1);

        return dto;
    }


}
