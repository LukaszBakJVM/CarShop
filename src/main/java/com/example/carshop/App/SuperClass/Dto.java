package com.example.carshop.App.SuperClass;

public class Dto {
    private long id;
    private String mark;
    private String model;
    private String serialNumber;
    private String partsBrand;
    private String price;
    private int quantity;
    private String category;
    private byte[] photoDto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPartsBrand() {
        return partsBrand;
    }

    public void setPartsBrand(String partsBrand) {
        this.partsBrand = partsBrand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getPhotoDto() {
        return photoDto;
    }

    public void setPhotoDto(byte[] photoDto) {
        this.photoDto = photoDto;
    }
}
