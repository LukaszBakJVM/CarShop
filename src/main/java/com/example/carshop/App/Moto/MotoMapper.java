package com.example.carshop.App.Moto;



import com.example.carshop.App.Car.Category.Category;
import com.example.carshop.App.Car.Category.CategoryRepository;
import com.example.carshop.App.Compononent.PhotoMapper;

import com.example.carshop.App.Shop.Basket.MotoParts.MotoPartsBasketDto;
import org.springframework.stereotype.Service;



@Service

public class MotoMapper {
    private final CategoryRepository categoryRepository;
    private final PhotoMapper photoMapper;


    public MotoMapper(CategoryRepository categoryRepository, PhotoMapper photoMapper) {
        this.categoryRepository = categoryRepository;
        this.photoMapper = photoMapper;
    }

   public MotoParts map(MotoDto dto) {
        MotoParts motoParts = new MotoParts();
        motoParts.setId(dto.getId());
        motoParts.setMark(dto.getMark());
        motoParts.setModel(dto.getModel());
        motoParts.setSerialnumber(dto.getSerialNumber());
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

  public   MotoDto map(MotoParts parts) {
        MotoDto dto = new MotoDto();
        dto.setId(parts.getId());
        dto.setMark(parts.getMark());
        dto.setModel(parts.getModel());
        dto.setSerialNumber(parts.getSerialnumber());
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
    MotoPartsBasketDto basket(MotoDto car){
        MotoPartsBasketDto dto = new MotoPartsBasketDto();


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



