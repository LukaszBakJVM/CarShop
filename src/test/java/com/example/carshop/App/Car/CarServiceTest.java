package com.example.carshop.App.Car;

import com.example.carshop.App.Car.Category.Category;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;


import java.util.Optional;



import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.*;



@SpringBootTest
@ActiveProfiles()





class CarServiceTest {

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarMapper carMapper;

    private final String SERIAL_NUMBER = "2";


    private final int PAGE_SIZE = 5;
    private final int PAGE = 0;






    @Test
    void saveNotExist() {
        Optional<Car> bySerialNumber = carRepository.findBySerialNumber("2");
        bySerialNumber.isEmpty();
        Car save = carRepository.save(car());
        CarDto map = carMapper.map(save);
        assertEquals(carDto(),map);

    }
    @Test
    void saveExist() {

        Optional<Car> bySerialNumber = carRepository.findBySerialNumber("2FMDK3GC4AB016773");
        Car car = bySerialNumber.get();
        car.setQuantity(car.getQuantity()+22);
        Car save = carRepository.save(car);
        CarDto map = carMapper.map(save);
        assertEquals(23,map.getQuantity());

    }

    @Test
    void delete() {
        Car car = carRepository.findBySerialNumber("2FMDK3GC4AB016773").orElseThrow();
        carRepository.delete(car);
        Optional<Car> bySerialNumber = carRepository.findBySerialNumber("2FMDK3GC4AB016773");
        assertEquals(bySerialNumber,Optional.empty());


    }


    @Test
    void findAll() throws InstantiationException, IllegalAccessException {





    }



    @Test
    void sellParts() {
    }

    @Test
    void updateAfterPurchase() {
    }

    @Test
   
    void findBySerialNumber() {


        when(carRepository.findBySerialNumber(SERIAL_NUMBER)).thenReturn(Optional.of(car()));
        when(carMapper.map(car())).thenReturn(carDto());


       CarDto bySerialNumber = carService.findBySerialNumber(SERIAL_NUMBER).orElseThrow();



        assertEquals(bySerialNumber,carDto());











       










    }



    @Test
    void count() {
    }

    @Test
    void saveParam() {
    }

    @Test
    void fileTyp() {
    }
    private Car car(){
          Car car = new Car();
          Category category = new Category();

        category.setName("silnik");


        car.setMark("vw");
        car.setModel("passat");
        car.setSerialNumber("2");
        car.setPartsBrand("febi");
        car.setPrice(BigDecimal.valueOf(10.1));
        car.setQuantity(2);
        car.setCategory(category);
        return car;
    }

    private Car car1(){
        Car car = new Car();
        Category category = new Category();

        category.setName("silnik");


        car.setMark("vw");
        car.setModel("passat");
        car.setSerialNumber("2");
        car.setPartsBrand("febi");
        car.setPrice(BigDecimal.valueOf(10.1));
        car.setQuantity(24);
        car.setCategory(category);
        return car;
    }

    private CarDto carDto(){
       CarDto dto = new CarDto();




        dto.setMark("vw");
        dto.setModel("passat");
        dto.setSerialNumber("2");
        dto.setPartsBrand("febi");
        dto.setPrice(BigDecimal.valueOf(10.1));
        dto.setQuantity(2);
        dto.setCategory("silnik");
        return dto;
    }

    private CarDto carDto1(){
        CarDto dto = new CarDto();




        dto.setMark("vw");
        dto.setModel("passat");
        dto.setSerialNumber("22");
        dto.setPartsBrand("febi");
        dto.setPrice(BigDecimal.valueOf(10.1));
        dto.setQuantity(22);
        dto.setCategory("silnik");
        return dto;
    }
}