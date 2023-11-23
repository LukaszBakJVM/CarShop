package com.example.carshop.Vaadin.Car;

import com.example.carshop.App.Car.CarDto;
import com.example.carshop.App.Car.CarService;
import com.example.carshop.Vaadin.ButtonReturn;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@Route("car/sell")

public class SellPart extends VerticalLayout {
    private final CarService carService;
    private TextField serialNumber;
    private TextField quantity;

    public SellPart(CarService carService) {
        this.carService = carService;
        FormLayout formLayout = new FormLayout();
        serialNumber = new TextField("Wpisz numer seryjny");
        quantity = new TextField("Wpisz ilość");

        Button updateButton = new Button("Zatwierdz Sprzedaż");
        ButtonReturn buttonReturn = new ButtonReturn();
        buttonReturn.returnToIndex();
        formLayout.add(serialNumber,quantity,updateButton,buttonReturn);
        updateButton.addClickListener(e->sellParts());
        add(formLayout);


    }
    private void sellParts(){
        String value = serialNumber.getValue();
        int quantityUp  = Integer.parseInt(quantity.getValue());
        Optional<CarDto> carDto = carService.sellParts(value, quantityUp);
        if (carDto.isPresent()) {
            Notification.show("Sprzedane");
        } else {
            Notification.show("Numer seryjny lub za duża ilośc wpisana");
        }

    }
}
