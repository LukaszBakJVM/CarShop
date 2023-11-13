package com.example.carshop.Vaadin.Car;

import com.example.carshop.App.Car.CarDto;
import com.example.carshop.App.Car.CarService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.Set;

@Route("all")
public class FindAllParts extends VerticalLayout {
    private final CarService carService;
    private TextField serialNumberField;
    private Grid<CarDto> carGrid;


    public FindAllParts(CarService carService) {
        this.carService = carService;

        serialNumberField = new TextField("Enter Serial Number");
        Button searchButton = new Button("Search");
        searchButton.addClickListener(event -> searchBySerialNumber());

        carGrid = new Grid<>(CarDto.class);
        carGrid.setColumns("mark", "model", "serialNumber", "partsBrand", "price", "quantity", "category");
        Set<CarDto> serialNumber = findAllCarsBySerialNumber("wpisz nr seryjny");
        carGrid.setItems(serialNumber);
        add(serialNumberField,searchButton,carGrid);
    }
    private void searchBySerialNumber() {
        String serialNumber = serialNumberField.getValue();
        if (!serialNumber.isEmpty()) {
            Set<CarDto> cars = findAllCarsBySerialNumber(serialNumber);
            carGrid.setItems(cars);

        }
    }

 private Set<CarDto>findAllCarsBySerialNumber(String serialNumber){
        return carService.findAllBySerialNumber(serialNumber);
    }

}
