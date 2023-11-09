package com.example.carshop.App.Car;

import com.example.carshop.App.Car.Category.Category;
import com.example.carshop.App.Car.Category.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CarMapper {


 private final CategoryRepository categoryRepository;

    public CarMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    Car map(CarDto dto) {
        Car car = new Car();
        car.setId(dto.getId());
        car.setMark(dto.getMark());
        car.setModel(dto.getModel());
        car.setSerialnumber(dto.getSerialnumber());
        car.setPartsBrand(dto.getPartsBrand());
        car.setPrice(dto.getPrice());
        car.setQuantity(dto.getQuantity());
        Category category = categoryRepository.findById(dto.getCategory()).orElseThrow();
        car.setCategory(category);

        return car;
    }
    CarDto map(Car car){
        CarDto dto =new CarDto();
        dto.setId(car.getId());
        dto.setMark(car.getMark());
        dto.setModel(car.getModel());
        dto.setSerialnumber(dto.getSerialnumber());
        dto.setPartsBrand(dto.getPartsBrand());
        dto.setPrice(car.getPrice());
        dto.setQuantity(car.getQuantity());
        dto.setCategory(car.getCategory().getName());
        return dto;
    }
}
