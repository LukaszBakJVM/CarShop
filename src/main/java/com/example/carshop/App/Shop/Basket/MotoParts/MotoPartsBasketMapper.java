package com.example.carshop.App.Shop.Basket.MotoParts;

import com.example.carshop.App.Car.Category.Category;
import com.example.carshop.App.Car.Category.CategoryRepository;

import com.example.carshop.App.Compononent.PhotoMapper;
import org.springframework.stereotype.Service;



@Service
public class MotoPartsBasketMapper {

    private final CategoryRepository categoryRepository;
    private final PhotoMapper photoMapper;

    public MotoPartsBasketMapper(CategoryRepository categoryRepository, PhotoMapper photoMapper) {
        this.categoryRepository = categoryRepository;
        this.photoMapper = photoMapper;
    }


    public MotoPartsBasket map(MotoPartsBasketDto dto) {
        MotoPartsBasket motoParts = new MotoPartsBasket();
        motoParts.setId(dto.getId());
        motoParts.setMark(dto.getMark());
        motoParts.setModel(dto.getModel());
        motoParts.setSerialNumber(dto.getSerialNumber());
        motoParts.setPartsBrand(dto.getPartsBrand());
        motoParts.setPrice(dto.getPrice());
        motoParts.setQuantity(dto.getQuantity());
        if (dto.getPhotoDto()!=null) {
            byte[] bytes = photoMapper.compressFile(dto.getPhotoDto());
            motoParts.setPhoto(bytes);
        }
        Category category = categoryRepository.findById(dto.getCategory()).orElseThrow();
        motoParts.setCategory(category);
        return motoParts;
    }

    public   MotoPartsBasketDto map(MotoPartsBasket parts) {
        MotoPartsBasketDto dto = new MotoPartsBasketDto();
        dto.setId(parts.getId());
        dto.setMark(parts.getMark());
        dto.setModel(parts.getModel());
        dto.setSerialNumber(parts.getSerialNumber());
        dto.setPartsBrand(parts.getPartsBrand());
        dto.setPrice(parts.getPrice());
        dto.setQuantity(parts.getQuantity());
        if (parts.getPhoto()!=null) {
            byte[] bytes = photoMapper.decompressFile(parts.getPhoto());
            dto.setPhotoDto(bytes);
        }
        dto.setCategory(parts.getCategory().getName());
        return dto;
    }


}

