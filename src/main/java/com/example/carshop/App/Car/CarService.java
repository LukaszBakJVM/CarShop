package com.example.carshop.App.Car;


import com.example.carshop.App.Exception.NotFoundException;
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
    private final int PAGE_SIZE = 2;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
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

    public Optional<CarDto> sellParts(String serialNumber, int quantity) {
        Optional<Car> bySerialnumber = carRepository.findBySerialNumber(serialNumber);
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

    public Optional<CarDto> findBySerialNumber(String serialNumber) {
        Optional<Car> bySerialnumber = carRepository.findBySerialNumber(serialNumber);
        if (bySerialnumber.isPresent()) {
            Car car = bySerialnumber.get();
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


}