package com.example.carshop.App.Moto;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotoRepository extends JpaRepository<MotoParts,Long> {
    Optional<MotoParts> findBySerialnumber(String serialNumber);

}