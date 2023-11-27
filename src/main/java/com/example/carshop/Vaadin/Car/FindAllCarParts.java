package com.example.carshop.Vaadin.Car;

import com.example.carshop.App.Car.CarDto;
import com.example.carshop.App.Car.CarService;
import com.example.carshop.Vaadin.ButtonReturn;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
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

        carGrid.addColumn("photoDto").setHeader("File");

        Set<CarDto> serialNumber = findAllCarsBySerialNumber("wpisz nr seryjny");
        for (CarDto c : serialNumber) {
            String fileType = c.getFileType();
            if (fileType.equals("image")) {
                Image image = createImageComponent(c.getPhotoDto());
                add(image);
            } else if (fileType.equals("application/pdf")) {
                Div pdfViewer = new Div();
                pdfViewer.setWidth("100%");
                pdfViewer.setHeight("500px");

                pdfViewer.getElement().executeJs("this.data = new Uint8Array($0);", c.getPhotoDto());
                add(pdfViewer);
            } else if (fileType.equals("text")) {
                String textContent = new String(c.getPhotoDto());
                add(textContent);

            } else {
                add("Nieobsługiwany rodzaj pliku");
            }

        }
        carGrid.setItems(serialNumber);
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
        return carService.findAllBySerialNumber(serialNumber, currentPage);
    }


    private Image createImageComponent(byte[] photo) {
        Image image = new Image();
        if (photo != null) {
            StreamResource resource = new StreamResource("image.jpg", () -> new ByteArrayInputStream(photo));
            image.setSrc(resource);
        }
        image.setWidth("150px");
        image.setHeight("150px");
        return image;
    }

    private int numberOfPage(int pageSize, int size) {
        return size / pageSize;


    }


   /* private StreamResource createStreamResource(byte[] photo,String s) {

        String fileName = "file"; // Domyślna nazwa pliku

        if (isJpgImage(carDto.getPhotoDto())) {
            fileName = "image.jpg";
        } else if (isPdfDocument(carDto.getPhotoDto())) {
            fileName = "document.pdf";
        } else if (isTextFile(carDto.getPhotoDto())) {
            fileName = "file.txt";
        }

        return new StreamResource(fileName, () -> new ByteArrayInputStream(photo));
    }*/


}





