package com.example.carshop.App.Moto;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MotoService {
    private final MotoRepository motoRepository;
    private final MotoMapper motoMapper;

    public MotoService(MotoRepository motoRepository, MotoMapper motoMapper) {
        this.motoRepository = motoRepository;
        this.motoMapper = motoMapper;
    }
    public MotoDto save(MotoDto motoDto){
        MotoParts motoParts = motoMapper.map(motoDto);
        Optional<MotoParts> bySerialnumber = motoRepository.findBySerialnumber(motoParts.getSerialnumber());
        if (bySerialnumber.isPresent()){
            MotoParts quantity = bySerialnumber.get();
            int i = quantity.getQuantity() + motoParts.getQuantity();
            quantity.setQuantity(i);
            MotoParts saveFound = motoRepository.save(quantity);
            return motoMapper.map(saveFound);
        }
        MotoParts save = motoRepository.save(motoParts);
        return motoMapper.map(save);


    }
}
