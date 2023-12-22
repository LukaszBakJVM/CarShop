package com.example.carshop.App.Shop;


import com.example.carshop.App.Moto.MotoDto;
import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasketDto;


import java.util.Set;

public class ShoppingCartDto {
    private long basketId;
    private String personEmail;
     private Set<CarPartsBasketDto>carDto;
     private Set<MotoDto>motoDto;

    public long getBasketId() {
        return basketId;
    }

    public void setBasketId(long basketId) {
        this.basketId = basketId;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public Set<CarPartsBasketDto> getCarDto() {
        return carDto;
    }

    public void setCarDto(Set<CarPartsBasketDto> carDto) {
        this.carDto = carDto;
    }

    public Set<MotoDto> getMotoDto() {
        return motoDto;
    }

    public void setMotoDto(Set<MotoDto> motoDto) {
        this.motoDto = motoDto;
    }
}
