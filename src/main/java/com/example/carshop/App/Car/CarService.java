package com.example.carshop.App.Car;


import com.example.carshop.App.Exception.NotFoundException;
import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasket;
import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasketDto;
import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasketMapper;
import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasketRepository;
import com.example.carshop.App.Shop.ShoppingCart;

import com.example.carshop.App.Shop.ShoppingCartRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    private final CarPartsBasketMapper carPartsBasketMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CarPartsBasketRepository carPartsBasketRepository;
    private final int PAGE_SIZE = 5;

    public CarService(CarRepository carRepository, CarMapper carMapper, CarPartsBasketMapper carPartsBasketMapper, ShoppingCartRepository shoppingCartRepository, CarPartsBasketRepository carPartsBasketRepository) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.carPartsBasketMapper = carPartsBasketMapper;
        this.shoppingCartRepository = shoppingCartRepository;
        this.carPartsBasketRepository = carPartsBasketRepository;
    }


    public CarDto save(CarDto carDto)  {
        Car car = carMapper.map(carDto);
        Optional<Car> bySerialNumber = carRepository.findBySerialNumber(car.getSerialnumber());
        if (bySerialNumber.isPresent()) {
            Car quantity = bySerialNumber.get();
            int i = quantity.getQuantity() + car.getQuantity();
            quantity.setQuantity(i);
            Car save = carRepository.save(quantity);
            return carMapper.map(save);
        }
        Car save = carRepository.save(car);
        return carMapper.map(save);
    }

    public void delete(String serial) {
        Optional<Car> bySerialNumber = carRepository.findBySerialNumber(serial);
     if (bySerialNumber.isPresent()){
         Car car = bySerialNumber.get();
         carRepository.delete(car);
     }else {
         throw new NotFoundException();
     }

    }

    public Set<CarDto> findAllBySerialNumber(String serialNumber, int page) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
        return carRepository.findCarBySerialNumberContainingIgnoreCase(serialNumber, pageRequest)
                .getContent()
                .stream().map(carMapper::map)
                .collect(Collectors.toSet());
    }

    public Set<CarDto> findAll(int page) {

        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);

        return carRepository.findAll(pageRequest).getContent()
                .stream().map(carMapper::map)
                .collect(Collectors.toSet());

    }

    public void sellParts(String serialNumber, int quantity , String email) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByPersonEmail(email).orElseThrow();


        Optional<Car> bySerialNumber = carRepository.findBySerialNumber(serialNumber);
        if (bySerialNumber.isPresent()) {
            Car q = bySerialNumber.get();

            if (q.getQuantity() > 0 && q.getQuantity() >= quantity) {
                CarDto map1 = carMapper.map(q);
                map1.setQuantity(quantity);
                CarPartsBasketDto basket = carMapper.basket(map1);
                CarPartsBasket map2 = carPartsBasketMapper.map(basket);

                Optional<CarPartsBasket> bySerialNumberExist =
                        carPartsBasketRepository.findBySerialNumberAndShoppingCartsId(map2.getSerialnumber(), shoppingCart.getId());
                if (bySerialNumberExist.isPresent()) {
                    CarPartsBasket carPartsBasket1 = bySerialNumberExist.get();
                    shoppingCart.getCarsParts().remove(carPartsBasket1);
                    carPartsBasket1.setQuantity(carPartsBasket1.getQuantity() + quantity);
                    carPartsBasketRepository.save(carPartsBasket1);
                    shoppingCart.getCarsParts().add(carPartsBasket1);


                } else {
                    CarPartsBasket save = carPartsBasketRepository.save(map2);
                    shoppingCart.getCarsParts().add(save);


                }

                shoppingCartRepository.save(shoppingCart);

            }

        }
    }

       public void updateAfterPurchase(String serialNumber ,int quantity){
            Car car = carRepository.findBySerialNumber(serialNumber).orElseThrow();
            car.setQuantity(car.getQuantity()-quantity);
            carRepository.save(car);



        }






    public Optional<CarDto> findBySerialNumber(String serialNumber) {
        Optional<Car> bySerialNumber = carRepository.findBySerialNumber(serialNumber);
        if (bySerialNumber.isPresent()) {
            Car car = bySerialNumber.get();
            CarDto map = carMapper.map(car);
            return Optional.of(map);
        }
        return Optional.empty();
    }



    public long count(){
        return carRepository.count();
    }
    CarDto saveParam( String mark,  String model, String serialNumber,
                      String partBrands,  BigDecimal price,  int quantity ,
                      String category,  MultipartFile file){
        CarDto dto = new CarDto();

        dto.setMark(mark);
        dto.setModel(model);
        dto.setSerialNumber(serialNumber);
        dto.setPartsBrand(partBrands);
        dto.setPrice(price);
        dto.setQuantity(quantity);
        try {
            dto.setPhotoDto(file.getBytes());
        } catch (IOException e) {
           e.getCause();
        }
        dto.setCategory(category);
        return dto;

    }
     String fileTyp(byte[] photoByte) {
        if (photoByte.length < 5){
            photoByte = new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
        }
        String fileType = "unknown file type";
        if ((photoByte[0] == (byte) 0xFF) && (photoByte[1] == (byte) 0xD8)) {
            fileType = "image";

        } else if ((photoByte[0] == (byte) 0x25) && (photoByte[1] == (byte) 0x50) &&
                (photoByte[2] == (byte) 0x44) && (photoByte[3] == (byte) 0x46)) {
            fileType = "pdf";

        } else if ((photoByte[0] >= 0x20 && photoByte[0] <= 0x7E) &&
                (photoByte[1] >= 0x20 && photoByte[1] <= 0x7E)) {
            fileType = "txt";
        }
        return fileType;
    }





}