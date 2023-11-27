package com.example.carshop.Vaadin.Car;

import com.example.carshop.App.Car.CarDto;
import com.example.carshop.App.Car.CarService;
import com.example.carshop.Vaadin.ButtonReturn;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;

@Route("car/all")

public class FindAllCarParts extends VerticalLayout {
    private final CarService carService;
    private TextField serialNumberField;
    private Grid<CarDto> carGrid;

    private int currentPage = 0;


    public FindAllCarParts(CarService carService) {
        this.carService = carService;


        serialNumberField = new TextField("Wpisz numer seryjny cześci");
        Button searchButton = new Button("Szukaj");
        searchButton.addClickListener(event -> searchBySerialNumber(currentPage));

        carGrid = new Grid<>(CarDto.class);
        carGrid.setColumns("mark", "model", "serialNumber", "partsBrand", "price", "quantity", "category");

        carGrid.addColumn(new ComponentRenderer<>(this::createImageComponent)).setHeader("File");


        Button prevButton = new Button("Poprzednia strona", e -> searchBySerialNumber(-1));
        Button nextButton = new Button("Nastepna strona", e -> searchBySerialNumber(1));

        HorizontalLayout navigationLayout = new HorizontalLayout(prevButton, nextButton);
        ButtonReturn buttonReturn = new ButtonReturn();
        buttonReturn.returnToIndex();

        add(serialNumberField, searchButton, carGrid, navigationLayout, buttonReturn);


    }


    private void searchBySerialNumber(int direction) {

        String serialNumber = serialNumberField.getValue();

        if (!serialNumber.isEmpty()) {
            Set<CarDto> cars = findAllCarsBySerialNumber(serialNumber);

            currentPage += direction;
                carGrid.setItems(cars);

        } else {
            Set<CarDto> all = carService.findAll(currentPage);

            currentPage += direction;
               carGrid.setItems(all);

        }
    }

    private Set<CarDto> findAllCarsBySerialNumber(String serialNumber) {
        return carService.findAllBySerialNumber(serialNumber,currentPage);
    }



    private Component createImageComponent(CarDto carDto) {
        String contentType = carDto.getFileType();

        if (contentType.equals("image.jpg")) {

            Image image = new Image();
            if (carDto.getPhotoDto() != null) {
                StreamResource resource = new StreamResource("image.jpg", () -> new ByteArrayInputStream(carDto.getPhotoDto()));
                image.setSrc(resource);
            }
            image.setWidth("150px");
            image.setHeight("150px");
            return image;
        } else if (contentType.equals("application/pdf")) {


            Div pdfViewer = new Div();
            pdfViewer.setWidth("150px");
            pdfViewer.setHeight("150px");

            pdfViewer.getElement().executeJs("this.data = new Uint8Array($0);", carDto.getPhotoDto());
            return pdfViewer;
        } else if (contentType.equals("text")) {

            TextArea textArea = new TextArea();
            textArea.setValue(new String(carDto.getPhotoDto(), StandardCharsets.UTF_8));
            textArea.setWidth("150px");
            textArea.setHeight("150px");
            return textArea;
        } else {

            return new Span("Nieobsługiwany rodzaj pliku");
        }
    }

    private int numberOfPage(int pageSize ,int size) {
        return size/pageSize;


    }



    private boolean isJpgImage(byte[] imageData) {

        return  (imageData[0] == (byte) 0xFF) && (imageData[1] == (byte) 0xD8);
    }




}
