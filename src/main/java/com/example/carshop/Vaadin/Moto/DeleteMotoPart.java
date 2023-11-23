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

@Route("moto/delete")
public class DeleteMotoPart extends VerticalLayout {
    private final MotoService motoService;
    private TextField serialNumber;
    public DeleteMotoPart(MotoService motoService) {
        this.motoService = motoService;

        FormLayout formLayout = new FormLayout();
        serialNumber = new TextField("Wpisz numer seryjny");
        Button deleteButton = new Button("Usuń część");
        ButtonReturn buttonReturn = new ButtonReturn();
        buttonReturn.returnToIndex();
        formLayout.add(serialNumber,deleteButton,buttonReturn);
        deleteButton.addClickListener(e -> delete());

        add(formLayout);
    }

    private void delete() {
        String value = serialNumber.getValue();
        Optional<MotoDto> bySerialNumber = motoService.findBySerialNumber(value);
        if (bySerialNumber.isPresent()){
            motoService.delete(value);
            Notification.show("Usunieto");
        } else {
            Notification.show("Błąd");
        }

    }

}
