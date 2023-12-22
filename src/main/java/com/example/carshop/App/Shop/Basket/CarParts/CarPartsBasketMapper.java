package com.example.carshop.App.Shop.Basket.CarParts;


import com.example.carshop.App.Car.Category.Category;
import com.example.carshop.App.Car.Category.CategoryRepository;
import com.example.carshop.App.Compononent.PhotoMapper;
import org.springframework.stereotype.Service;



@Service
public class CarPartsBasketMapper {
    private final CategoryRepository repository;
    private final PhotoMapper photoMapper;

    public CarPartsBasketMapper(CategoryRepository repository, PhotoMapper photoMapper) {
        this.repository = repository;
        this.photoMapper = photoMapper;
    }

    public CarPartsBasket map(CarPartsBasketDto dto)  {
       CarPartsBasket car = new CarPartsBasket();
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
        Category category = repository.findById(dto.getCategory()).orElseThrow();
        car.setCategory(category);

        return car;
    }

    public   CarPartsBasketDto map(CarPartsBasket car) {
        CarPartsBasketDto dto = new CarPartsBasketDto();
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

}

