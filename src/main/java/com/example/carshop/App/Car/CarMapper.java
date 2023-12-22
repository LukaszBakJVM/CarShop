package com.example.carshop.App.Car;

import com.example.carshop.App.Car.Category.Category;
import com.example.carshop.App.Car.Category.CategoryRepository;

import com.example.carshop.App.Compononent.PhotoMapper;
import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasketDto;
import org.springframework.stereotype.Service;





@Service
public class CarMapper {


    private final CategoryRepository categoryRepository;
    private final PhotoMapper photoMapper;



    public CarMapper(CategoryRepository categoryRepository, PhotoMapper photoMapper) {
        this.categoryRepository = categoryRepository;

        this.photoMapper = photoMapper;
    }

   public Car map(CarDto dto)  {
        Car car = new Car();
        car.setId(dto.getId());
        car.setMark(dto.getMark());
        car.setModel(dto.getModel());
        car.setSerialnumber(dto.getSerialNumber());
        car.setPartsBrand(dto.getPartsBrand());
        car.setPrice(dto.getPrice());
        car.setQuantity(dto.getQuantity());
        if (dto.getPhotoDto() != null) {
            byte[] bytes = photoMapper.compressFile(dto.getPhotoDto());
            car.setPhoto(bytes);
        }
        Category category = categoryRepository.findById(dto.getCategory()).orElseThrow();
        car.setCategory(category);

        return car;
    }

  public   CarDto map(Car car) {
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setMark(car.getMark());
        dto.setModel(car.getModel());
        dto.setSerialNumber(car.getSerialnumber());
        dto.setPartsBrand(car.getPartsBrand());
        dto.setPrice(car.getPrice());
        dto.setQuantity(car.getQuantity());
        if (car.getPhoto() != null) {
            byte[] bytes = photoMapper.decompressFile(car.getPhoto());
            dto.setPhotoDto(bytes);
        }
        dto.setCategory(car.getCategory().getName());
        return dto;
    }

    CarPartsBasketDto basket(CarDto car){
        CarPartsBasketDto dto = new CarPartsBasketDto();


        dto.setMark(car.getMark());
        dto.setModel(car.getModel());
        dto.setSerialNumber(car.getSerialNumber());
        dto.setPartsBrand(car.getPartsBrand());
        dto.setPrice(car.getPrice());
        dto.setQuantity(car.getQuantity());

            dto.setPhotoDto(car.getPhotoDto());

        dto.setCategory(car.getCategory());
        return dto;
    }










}
