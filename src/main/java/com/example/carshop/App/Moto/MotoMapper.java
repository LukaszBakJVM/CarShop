package com.example.carshop.App.Moto;


import com.example.carshop.App.Car.Category.Category;
import com.example.carshop.App.Car.Category.CategoryRepository;
import org.springframework.stereotype.Service;

@Service

public class MotoMapper {
    private final CategoryRepository categoryRepository;


    public MotoMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    MotoParts map(MotoDto dto) {
        MotoParts motoParts = new MotoParts();
        motoParts.setId(dto.getId());
        motoParts.setMark(dto.getMark());
        motoParts.setModel(dto.getModel());
        motoParts.setSerialnumber(dto.getSerialNumber());
        motoParts.setPartsBrand(dto.getPartsBrand());
        motoParts.setPrice(dto.getPrice());
        motoParts.setQuantity(dto.getQuantity());
        Category category = categoryRepository.findById(dto.getCategory()).orElseThrow();
        motoParts.setCategory(category);
        return motoParts;
    }

    MotoDto map(MotoParts parts) {
        MotoDto dto = new MotoDto();
        dto.setId(parts.getId());
        dto.setMark(parts.getMark());
        dto.setModel(parts.getModel());
        dto.setSerialNumber(parts.getSerialnumber());
        dto.setPartsBrand(parts.getPartsBrand());
        dto.setPrice(parts.getPrice());
        dto.setQuantity(parts.getQuantity());
        dto.setCategory(parts.getCategory().getName());
        return dto;
    }
}
