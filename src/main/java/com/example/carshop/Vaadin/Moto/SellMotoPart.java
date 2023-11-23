package com.example.carshop.Vaadin.Moto;


import com.example.carshop.App.Moto.MotoDto;
import com.example.carshop.App.Moto.MotoService;
import com.example.carshop.Vaadin.ButtonReturn;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@Route("moto/sell")
public class SellMotoPart extends VerticalLayout {
    private final MotoService motoService;
    private TextField serialNumber;
    private TextField quantity;

    public SellMotoPart(MotoService motoService) {
        this.motoService = motoService;

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
        Optional<MotoDto> carDto = motoService.sellParts(value, quantityUp);
        if (carDto.isPresent()) {
            Notification.show("Sprzedane");
        } else {
            Notification.show("Numer seryjny lub za duża ilośc wpisana");
        }

    }

}
