package com.example.carshop.App.Moto;



import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MotoService {
    private final MotoRepository motoRepository;
    private final MotoMapper motoMapper;
    private final int PAGE_SIZE = 2;

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
    public void delete(String serial) {
        Optional<MotoParts> bySerialnumber = motoRepository.findBySerialnumber(serial);
        bySerialnumber.ifPresent(motoRepository::delete);

    }

    public Set<MotoDto> findAllBySerialNumber(String serialNumber, int page) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
        return motoRepository.findCarBySerialnumberContainingIgnoreCase(serialNumber, pageRequest)
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

    public Optional<MotoDto> sellParts(String serialNumber, int quantity) {
        Optional<MotoParts> bySerialnumber = motoRepository.findBySerialnumber(serialNumber);
        if (bySerialnumber.isPresent()) {
            MotoParts q = bySerialnumber.get();
            if (q.getQuantity() > 0 && q.getQuantity() >= quantity) {
                int update = q.getQuantity() - quantity;
                q.setQuantity(update);
                motoRepository.save(q);
                return Optional.of(motoMapper.map(q));
            }
        }
        return Optional.empty();
    }

    public Optional<MotoDto> findBySerialNumber(String serialNumber) {
        Optional<MotoParts> bySerialnumber = motoRepository.findBySerialnumber(serialNumber);
        if (bySerialnumber.isPresent()) {
            MotoParts motoParts = bySerialnumber.get();
            MotoDto map = motoMapper.map(motoParts);
            return Optional.of(map);
        }
        return Optional.empty();
    }
}