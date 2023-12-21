package com.example.carshop.App.Shop;

import com.example.carshop.App.Car.CarDto;
import com.example.carshop.App.Moto.MotoDto;

import java.math.BigDecimal;
import java.util.Set;

public class ShoppingCartDto {
    private long basketId;
    private int quantity;
    private BigDecimal sum;
    private String personEmail;
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

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
