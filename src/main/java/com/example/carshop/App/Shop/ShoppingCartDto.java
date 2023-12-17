package com.example.carshop.App.Shop;

import com.example.carshop.App.Car.CarDto;
import com.example.carshop.App.Moto.MotoDto;

import java.util.Set;

public class ShoppingCartDto {
    private long basketId;
     private Set<CarDto>carDto;
     private Set<MotoDto>motoDto;

    public long getBasketId() {
        return basketId;
    }

    public void setBasketId(long basketId) {
        this.basketId = basketId;
    }

    public Set<CarDto> getCarDto() {
        return carDto;
    }

    public void setCarDto(Set<CarDto> carDto) {
        this.carDto = carDto;
    }

    public Set<MotoDto> getMotoDto() {
        return motoDto;
    }

    public void setMotoDto(Set<MotoDto> motoDto) {
        this.motoDto = motoDto;
    }
}
