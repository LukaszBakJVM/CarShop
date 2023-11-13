package com.example.carshop.App.Car;

import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public CarDto save(CarDto carDto) {
        Car car = carMapper.map(carDto);
        Optional<Car> bySerialnumber = carRepository.findBySerialnumber(car.getSerialnumber());
        if (bySerialnumber.isPresent()) {
            Car quantity = bySerialnumber.get();
            int i = quantity.getQuantity() + car.getQuantity();
            quantity.setQuantity(i);
            Car save = carRepository.save(quantity);
            return carMapper.map(save);
        }
        Car save = carRepository.save(car);
        return carMapper.map(save);
    }

    public void delete(String serial) {
        Optional<Car> bySerialnumber = carRepository.findBySerialnumber(serial);
        bySerialnumber.ifPresent(carRepository::delete);

    }

    public Set<CarDto> findAllBySerialNumber(String serialNumber) {
        return carRepository.findCarBySerialnumberContainingIgnoreCase(serialNumber)
                .stream().map(carMapper::map)
                .collect(Collectors.toSet());
    }
    public Set<CarDto>findAll(){
        return carRepository.findAll()
                .stream().map(carMapper::map)
                .collect(Collectors.toSet());
    }

    public Optional<CarDto> sellParts(String serialNumber, int quantity) {
        Optional<Car> bySerialnumber = carRepository.findBySerialnumber(serialNumber);
        if (bySerialnumber.isPresent()) {
            Car q = bySerialnumber.get();
            if (q.getQuantity() > 0 && q.getQuantity() >= quantity) {
                int update = q.getQuantity() - quantity;
                q.setQuantity(update);
                carRepository.save(q);
                return Optional.of(carMapper.map(q));
            }
        }
        return Optional.empty();
    }

 public    Optional<CarDto> findBySerialNumber(String serialNumber) {
        Optional<Car> bySerialnumber = carRepository.findBySerialnumber(serialNumber);
        if (bySerialnumber.isPresent()) {
            Car car = bySerialnumber.get();
            CarDto map = carMapper.map(car);
            return Optional.of(map);
        }
        return Optional.empty();
    }
}
