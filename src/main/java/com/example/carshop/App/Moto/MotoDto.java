package com.example.carshop.App.Moto;

import com.example.carshop.App.SuperClass.Dto;

public class MotoDto extends Dto {
    private long id;
    public MotoDto() {
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
