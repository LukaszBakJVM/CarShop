package com.example.carshop.Vaadin.Car;

import com.example.carshop.App.Car.CarService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


    @Route("delete")
    public class DeleteCar extends VerticalLayout {
        private final CarService carService;


        public DeleteCar(CarService carService) {
            this.carService = carService;
            Button deleteButton = new Button("usun wszystko");
            FormLayout formLayout = new FormLayout();
            deleteButton.addClickListener(e -> edelete());
            formLayout.add(deleteButton);
            add(formLayout);
        }

        private void edelete() {
            carService.deleteAll();

        }
    }

