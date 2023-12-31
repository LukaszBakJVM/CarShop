package com.example.carshop.App.SuperClass;

import jakarta.persistence.*;

import java.math.BigDecimal;

import java.util.Objects;

@MappedSuperclass
public class Parts {

    private String mark;
    private String model;
    private String serialNumber;
    private String partsBrand;
    private BigDecimal price;
    private int quantity;
    @Lob
    @Column(length = 500000000)
    private   byte[] photo;




    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialnumber() {
        return serialNumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialNumber = serialnumber;
    }

    public String getPartsBrand() {
        return partsBrand;
    }

    public void setPartsBrand(String partsBrand) {
        this.partsBrand = partsBrand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parts parts = (Parts) o;
        return Objects.equals(mark, parts.mark) && Objects.equals(model, parts.model) && Objects.equals(serialNumber, parts.serialNumber) && Objects.equals(partsBrand, parts.partsBrand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark, model, serialNumber, partsBrand);
    }


}
