package com.example.carshop.App.Car;

import com.example.carshop.App.SuperClass.Dto;


public class CarDto extends Dto {
    private byte[] photoDto;

    public CarDto() {
    }

    public byte[] getPhotoDto() {
        return photoDto;
    }

    public void setPhotoDto(byte[] photoDto) {
        this.photoDto = photoDto;
    }
}
