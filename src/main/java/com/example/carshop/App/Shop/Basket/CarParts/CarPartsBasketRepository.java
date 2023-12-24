package com.example.carshop.App.Shop.Basket.CarParts;




import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarPartsBasketRepository extends JpaRepository<CarPartsBasket,Long> {

    Optional<CarPartsBasket>findBySerialNumber(String serialNumber);
}
