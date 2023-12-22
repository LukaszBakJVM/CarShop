package com.example.carshop.App.Shop;



import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasketDto;
import com.example.carshop.App.Shop.Basket.MotoParts.MotoPartsBasketDto;


import java.util.Set;

public class ShoppingCartDto {
    private long basketId;
    private String personEmail;
     private Set<CarPartsBasketDto>carDto;
     private Set<MotoPartsBasketDto>motoDto;

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

    public Set<MotoPartsBasketDto> getMotoDto() {
        return motoDto;
    }

    public void setMotoDto(Set<MotoPartsBasketDto> motoDto) {
        this.motoDto = motoDto;
    }
}
