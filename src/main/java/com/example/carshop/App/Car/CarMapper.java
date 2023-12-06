package com.example.carshop.App.Car;

import com.example.carshop.App.Car.Category.Category;
import com.example.carshop.App.Car.Category.CategoryRepository;

import org.springframework.stereotype.Service;



import java.io.ByteArrayOutputStream;

import java.util.zip.Deflater;
import java.util.zip.Inflater;


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
        car.setSerialNumber(dto.getSerialNumber());
        car.setPartsBrand(dto.getPartsBrand());
        car.setPrice(dto.getPrice());
        car.setQuantity(dto.getQuantity());
        if (dto.getPhotoDto() != null) {
            byte[] bytes = compressFile(dto.getPhotoDto());
            car.setPhoto(bytes);
        }
        Category category = categoryRepository.findById(dto.getCategory()).orElseThrow();
        car.setCategory(category);

        return car;
    }

    CarDto map(Car car) {
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setMark(car.getMark());
        dto.setModel(car.getModel());
        dto.setSerialNumber(car.getSerialNumber());
        dto.setPartsBrand(car.getPartsBrand());
        dto.setPrice(car.getPrice());
        dto.setQuantity(car.getQuantity());
        if (car.getPhoto() != null) {
            byte[] bytes = decompressFile(car.getPhoto());
            dto.setPhotoDto(bytes);
        }
        dto.setCategory(car.getCategory().getName());
        return dto;
    }


    private byte[] compressFile(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_SPEED);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
            e.getCause();
        }
        return outputStream.toByteArray();
    }

    private byte[] decompressFile(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception e) {
            e.getCause();
        }
        return outputStream.toByteArray();
    }
}
