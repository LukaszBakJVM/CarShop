package com.example.carshop.App.Moto;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotoRepository extends JpaRepository<MotoParts,Long> {
    Optional<MotoParts> findBySerialnumber(String serialNumber);
    Page<MotoParts> findCarBySerialnumberContainingIgnoreCase(String serialNumber, Pageable pageable);
    Page<MotoParts>findAll(Pageable pageable);

}