package com.example.carshop.App.Shop.Basket.MotoParts;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotoPartsBasketRepository extends JpaRepository<MotoPartsBasket,Long> {
    Optional<MotoPartsBasket>findBySerialNumberAndShoppingCartsId(String serialNumber,long id);
}
