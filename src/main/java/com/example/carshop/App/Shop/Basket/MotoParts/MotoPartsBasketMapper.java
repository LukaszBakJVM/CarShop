package com.example.carshop.App.Shop.Basket.MotoParts;

import com.example.carshop.App.Car.Category.Category;
import com.example.carshop.App.Car.Category.CategoryRepository;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class MotoPartsBasketMapper {

    private final CategoryRepository categoryRepository;

    public MotoPartsBasketMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public MotoPartsBasket map(MotoPartsBasketDto dto) {
        MotoPartsBasket motoParts = new MotoPartsBasket();
        motoParts.setId(dto.getId());
        motoParts.setMark(dto.getMark());
        motoParts.setModel(dto.getModel());
        motoParts.setSerialnumber(dto.getSerialNumber());
        motoParts.setPartsBrand(dto.getPartsBrand());
        motoParts.setPrice(dto.getPrice());
        motoParts.setQuantity(dto.getQuantity());
        if (dto.getPhotoDto()!=null) {
            byte[] bytes = compressImage(dto.getPhotoDto());
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
        dto.setSerialNumber(parts.getSerialnumber());
        dto.setPartsBrand(parts.getPartsBrand());
        dto.setPrice(parts.getPrice());
        dto.setQuantity(parts.getQuantity());
        if (parts.getPhoto()!=null) {
            byte[] bytes = decompressImage(parts.getPhoto());
            dto.setPhotoDto(bytes);
        }
        dto.setCategory(parts.getCategory().getName());
        return dto;
    }

    private byte[] compressImage(byte[] data) {

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

    private byte[] decompressImage(byte[] data) {
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

