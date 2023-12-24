package com.example.carshop.App.Moto;






import com.example.carshop.App.Exception.NotFoundException;

import com.example.carshop.App.Shop.Basket.MotoParts.MotoPartsBasket;
import com.example.carshop.App.Shop.Basket.MotoParts.MotoPartsBasketDto;
import com.example.carshop.App.Shop.Basket.MotoParts.MotoPartsBasketMapper;
import com.example.carshop.App.Shop.Basket.MotoParts.MotoPartsBasketRepository;
import com.example.carshop.App.Shop.ShoppingCart;

import com.example.carshop.App.Shop.ShoppingCartRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MotoService {
    private final MotoRepository motoRepository;
    private final MotoMapper motoMapper;
   private final MotoPartsBasketMapper motoPartsBasketMapper;
   private final ShoppingCartRepository shoppingCartRepository;
   private final MotoPartsBasketRepository motoPartsBasketRepository;
    private final int PAGE_SIZE = 5;

    public MotoService(MotoRepository motoRepository, MotoMapper motoMapper, MotoPartsBasketMapper motoPartsBasketMapper,
                       ShoppingCartRepository shoppingCartRepository, MotoPartsBasketRepository motoPartsBasketRepository) {
        this.motoRepository = motoRepository;
        this.motoMapper = motoMapper;
        this.motoPartsBasketMapper = motoPartsBasketMapper;
        this.shoppingCartRepository = shoppingCartRepository;
        this.motoPartsBasketRepository = motoPartsBasketRepository;
    }


    public MotoDto save(MotoDto motoDto){
        MotoParts motoParts = motoMapper.map(motoDto);
        Optional<MotoParts> bySerialNumber = motoRepository.findBySerialNumber(motoParts.getSerialnumber());
        if (bySerialNumber.isPresent()){
            MotoParts quantity = bySerialNumber.get();
            int i = quantity.getQuantity() + motoParts.getQuantity();
            quantity.setQuantity(i);
            MotoParts saveFound = motoRepository.save(quantity);
            return motoMapper.map(saveFound);
        }
        MotoParts save = motoRepository.save(motoParts);
        return motoMapper.map(save);
    }
    public void delete(String serial) {
        Optional<MotoParts> bySerialNumber = motoRepository.findBySerialNumber(serial);
        if (bySerialNumber.isPresent()){
            MotoParts motoParts = bySerialNumber.get();
            motoRepository.delete(motoParts);
        }else {
            throw new NotFoundException();
        }

    }

    public Set<MotoDto> findAllBySerialNumber(String serialNumber, int page) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
        return motoRepository.findCarBySerialNumberContainingIgnoreCase(serialNumber, pageRequest)
                .getContent()
                .stream().map(motoMapper::map)
                .collect(Collectors.toSet());

    }


    public Set<MotoDto> findAll(int page) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
        return motoRepository.findAll(pageRequest).getContent()
                .stream().map(motoMapper::map)
                .collect(Collectors.toSet());

    }

    public void sellParts(String serialNumber, int quantity , String email) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByPersonEmail(email).orElseThrow();

        Optional<MotoParts> bySerialNumber = motoRepository.findBySerialNumber(serialNumber);
        if (bySerialNumber.isPresent()) {
            MotoParts q = bySerialNumber.get();

            if (q.getQuantity() > 0 && q.getQuantity() >= quantity) {

                MotoDto map1 = motoMapper.map(q);
                map1.setQuantity(quantity);
                MotoPartsBasketDto basket = motoMapper.basket(map1);
                MotoPartsBasket map2 = motoPartsBasketMapper.map(basket);
                Optional<MotoPartsBasket> bySerialNumberExist =
                        motoPartsBasketRepository.findBySerialNumberAndShoppingCartsId(map2.getSerialnumber(),shoppingCart.getId());
                if (bySerialNumberExist.isPresent()){
                    MotoPartsBasket motoPartsBasket = bySerialNumberExist.get();
                    shoppingCart.getMotoParts().remove(motoPartsBasket);
                    motoPartsBasket.setQuantity(motoPartsBasket.getQuantity()+quantity);
                    motoPartsBasketRepository.save(motoPartsBasket);
                    shoppingCart.getMotoParts().add(motoPartsBasket);


                } else {
                    MotoPartsBasket save = motoPartsBasketRepository.save(map2);
                    shoppingCart.getMotoParts().add(save);
                }

                shoppingCartRepository.save(shoppingCart);
            }
        }
    }

    public Optional<MotoDto> findBySerialNumber(String serialNumber) {
        Optional<MotoParts> bySerialNumber = motoRepository.findBySerialNumber(serialNumber);
        if (bySerialNumber.isPresent()) {
            MotoParts motoParts = bySerialNumber.get();
            MotoDto map = motoMapper.map(motoParts);
            return Optional.of(map);
        }
        return Optional.empty();
    }
    public long count(){
        return motoRepository.count();
    }

    MotoDto saveParam(String mark, String model, String serialNumber,
                     String partBrands, BigDecimal price, int quantity ,
                     String category, MultipartFile file){
        MotoDto dto = new MotoDto();

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
    String fileTyp(byte[] photoByte) {
        if (photoByte.length < 5){
            photoByte = new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
        }
        String fileType = "unknown file type";
        if ((photoByte[0] == (byte) 0xFF) && (photoByte[1] == (byte) 0xD8)) {
            fileType = "image";

        } else if ((photoByte[0] == (byte) 0x25) && (photoByte[1] == (byte) 0x50) &&
                (photoByte[2] == (byte) 0x44) && (photoByte[3] == (byte) 0x46)) {
            fileType = "pdf";

        } else if ((photoByte[0] >= 0x20 && photoByte[0] <= 0x7E) &&
                (photoByte[1] >= 0x20 && photoByte[1] <= 0x7E)) {
            fileType = "txt";
        }
        return fileType;
    }
}
