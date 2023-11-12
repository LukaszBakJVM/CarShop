package com.example.carshop.App.Car;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }
  public   CarDto save (CarDto carDto){
      Car car = carMapper.map(carDto);
      Optional<Car> bySerialnumber = carRepository.findBySerialnumber(car.getSerialnumber());
      if (bySerialnumber.isPresent()){
          Car quantity = bySerialnumber.get();
          int i = quantity.getQuantity() + car.getQuantity();
          quantity.setQuantity(i);
          Car save = carRepository.save(quantity);
          return carMapper.map(save);
      }
      Car save =carRepository.save(car);
        return carMapper.map(save);
    }
    public void deleteAll(){
        carRepository.deleteAll();
    }

}
