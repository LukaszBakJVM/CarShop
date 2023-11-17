package com.example.carshop.Vaadin.Car;

import com.example.carshop.App.Car.CarDto;
import com.example.carshop.App.Car.CarService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.util.Set;

@Route("all")
public class FindAllParts extends VerticalLayout {
    private final CarService carService;
    private TextField serialNumberField;
    private Grid<CarDto> carGrid;

    private int currentPage = 0;
    private int pageSize = 10;


    public FindAllParts(CarService carService) {
        this.carService = carService;

        serialNumberField = new TextField("Enter Serial Number");
        Button searchButton = new Button("Search");
        searchButton.addClickListener(event -> searchBySerialNumber());

        carGrid = new Grid<>(CarDto.class);
        carGrid.setColumns("mark", "model", "serialNumber", "partsBrand", "price", "quantity", "category");
        carGrid.addColumn(new ComponentRenderer<>(this::createImageComponent)).setHeader("Photo");

        Set<CarDto> serialNumber = findAllCarsBySerialNumber("wpisz nr seryjny");
        carGrid.setItems(serialNumber);
        Button prevButton = new Button("Previous", e -> navigate(-1));
        Button nextButton = new Button("Next", e -> navigate(1));

        HorizontalLayout navigationLayout = new HorizontalLayout(prevButton, nextButton);

        add(serialNumberField, searchButton, carGrid,navigationLayout);
    }

    private void searchBySerialNumber() {
        String serialNumber = serialNumberField.getValue();
        if (!serialNumber.isEmpty()) {
            Set<CarDto> cars = findAllCarsBySerialNumber(serialNumber);
            carGrid.setItems(cars);
        } else {
            Set<CarDto> all = findAll();
            carGrid.setItems(all);


        }
    }

    private Set<CarDto> findAllCarsBySerialNumber(String serialNumber) {
        return carService.findAllBySerialNumber(serialNumber,currentPage,pageSize);
    }

    private Set<CarDto> findAll() {
        return carService.findAll(currentPage,pageSize);
    }

    private Image createImageComponent(CarDto carDto) {
        Image image = new Image();
        if (carDto.getPhotoDto() != null) {
            StreamResource resource = new StreamResource("image.jpg", () -> new ByteArrayInputStream(carDto.getPhotoDto()));
            image.setSrc(resource);
        }
        image.setWidth("150px");
        image.setHeight("150px");
        return image;
    }
    private void navigate(int direction) {
        currentPage += direction;
        Set<CarDto> cars = findAll();
        carGrid.setItems(cars);
    }

}
