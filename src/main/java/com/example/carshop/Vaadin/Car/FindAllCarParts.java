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

        Set<CarDto> serialNumber = findAllCarsBySerialNumber("wpisz nr seryjny");


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
        return carService.findAllBySerialNumber(serialNumber,currentPage);
    }



    private Component createImageComponent(CarDto carDto) {
        String contentType = carDto.getFileType();

        if (contentType.equals("image")) {

            Image image = new Image();
            if (carDto.getPhotoDto() != null) {
                StreamResource resource = new StreamResource("image.jpg", () -> new ByteArrayInputStream(carDto.getPhotoDto()));
                image.setSrc(resource);
            }
            image.setWidth("150px");
            image.setHeight("150px");
            return image;
        } else if (contentType.equals("application/pdf")) {
            // Jeśli to plik PDF, można użyć komponentu PDFBrowser (wymaga dodatkowej biblioteki)
            // Przykład: https://vaadin.com/directory/component/pdf-browser
            Div pdfViewer = new Div();
            pdfViewer.setWidth("150px");
            pdfViewer.setHeight("150px");
            // Ustaw dane PDF
            pdfViewer.getElement().executeJs("this.data = new Uint8Array($0);", carDto.getPhotoDto());
            return pdfViewer;
        } else if (contentType.equals("text")) {
            // Jeśli to plik tekstowy, użyj komponentu TextArea
            TextArea textArea = new TextArea();
            textArea.setValue(new String(carDto.getPhotoDto(), StandardCharsets.UTF_8));
            textArea.setWidth("150px");
            textArea.setHeight("150px");
            return textArea;
        } else {
            // Obsługa innych rodzajów plików
            return new Span("Nieobsługiwany rodzaj pliku");
        }
    }

    private int numberOfPage(int pageSize ,int size) {
        return size/pageSize;


    }


    private StreamResource createStreamResource(CarDto carDto) {
        byte[] imageData = carDto.getPhotoDto(); // Załóżmy, że to są dane obrazu w formie bajtów
        String fileName = "file"; // Domyślna nazwa pliku

        if (isJpgImage(carDto.getPhotoDto())) {
            fileName = "image.jpg";
        } else if (isPdfDocument(carDto.getPhotoDto())) {
            fileName = "document.pdf";
        } else if (isTextFile(carDto.getPhotoDto())) {
            fileName = "file.txt";
        }

        return new StreamResource(fileName, () -> new ByteArrayInputStream(imageData));
    }

    private boolean isJpgImage(byte[] imageData) {

        return  (imageData[0] == (byte) 0xFF) && (imageData[1] == (byte) 0xD8);
    }

    private boolean isPdfDocument(byte[] imageData) {
        // Sprawdź, czy dane obrazu wskazują na plik PDF
        // Możesz to zrobić na podstawie magicznych bajtów dla plików PDF
        // Tu można wstawić kod sprawdzający, czy imageData to plik PDF
        return (imageData[0] == (byte) 0x25) &&  // %
                (imageData[1] == (byte) 0x50) &&  // P
                (imageData[2] == (byte) 0x44) &&  // D
                (imageData[3] == (byte) 0x46);
    }

    private boolean isTextFile(byte[] imageData) {
        // Sprawdź, czy dane obrazu wskazują na plik tekstowy
        // Możesz to zrobić na podstawie analizy zawartości imageData
        // Tu można wstawić kod sprawdzający, czy imageData to plik tekstowy
        return (imageData[0] >= 0x20 && imageData[0] <= 0x7E) &&
                (imageData[1] >= 0x20 &&imageData[1] <= 0x7E);
    }


}
