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



@Route("delete")
    public class DeletePart extends VerticalLayout {
        private final CarService carService;
        private TextField serialNumber;


        public DeletePart(CarService carService) {
            this.carService = carService;

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
            Optional<CarDto> bySerialNumber = carService.findBySerialNumber(value);
            if (bySerialNumber.isPresent()){
                carService.delete(value);
                Notification.show("Usunieto");
            } else {
                Notification.show("Błąd");
            }

        }
    }

