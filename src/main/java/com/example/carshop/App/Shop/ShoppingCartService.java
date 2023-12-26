package com.example.carshop.App.Shop;




import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasketDto;
import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasketMapper;
import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasketRepository;
import com.example.carshop.App.Shop.Basket.MotoParts.MotoPartsBasketDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ShoppingCartService {
    private final  ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CarPartsBasketRepository carPartsBasketRepository;
    private final CarPartsBasketMapper carPartsBasketMapper;
    private final int PAGE_SIZE = 5;


    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ShoppingCartMapper shoppingCartMapper,
                               CarPartsBasketRepository carPartsBasketRepository, CarPartsBasketMapper carPartsBasketMapper) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartMapper = shoppingCartMapper;


        this.carPartsBasketRepository = carPartsBasketRepository;
        this.carPartsBasketMapper = carPartsBasketMapper;
    }
 public ShoppingCartDto  findBasketByPersonEmail(String email){
     ShoppingCart shoppingCart = shoppingCartRepository.findByPersonEmail(email).orElseThrow();
   return shoppingCartMapper.map(shoppingCart);
    }

    ShoppingCartDto findById(long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow();
        return shoppingCartMapper.map(shoppingCart);
    }
    Set<ShoppingCartDto>findAll(String email,int page){
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
      return   shoppingCartRepository.findAByPersonEmail(email,pageRequest)
                .getContent()
              .stream().map(shoppingCartMapper::map).collect(Collectors.toSet());

    }
    void deletePartFromBasket(String email,String serialNumber){
        Optional<ShoppingCart> byPersonEmail = shoppingCartRepository.findByPersonEmail(email);
        byPersonEmail.isPresent();
        ShoppingCart shoppingCart = byPersonEmail.get();
        shoppingCart.getCarsParts().removeIf(car->car.getSerialnumber().equals(serialNumber));
        shoppingCart.getMotoParts().removeIf(moto->moto.getSerialnumber().equals(serialNumber));
        ShoppingCart save = shoppingCartRepository.save(shoppingCart);
        shoppingCartMapper.map(save);

    }
    BigDecimal sum(String email){
        ShoppingCart shoppingCart = shoppingCartRepository.findByPersonEmail(email).orElseThrow();
        ShoppingCartDto basketByPersonEmail = shoppingCartMapper.map(shoppingCart);
        Set<CarPartsBasketDto> carDto = basketByPersonEmail.getCarDto();
        BigDecimal carPartsSum = carDto.stream().map(basketSum -> basketSum.getPrice()
                        .multiply(BigDecimal.valueOf(basketSum.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Set<MotoPartsBasketDto> motoDto = basketByPersonEmail.getMotoDto();
        BigDecimal motoPartsSum = motoDto.stream().map(basketSum -> basketSum.getPrice()
                        .multiply(BigDecimal.valueOf(basketSum.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return  carPartsSum.add(motoPartsSum);
    }
    void saveAfterSell(ShoppingCartDto shoppingCartDto){
        ShoppingCart map = shoppingCartMapper.map(shoppingCartDto);
        shoppingCartRepository.save(map);

    }




    }













