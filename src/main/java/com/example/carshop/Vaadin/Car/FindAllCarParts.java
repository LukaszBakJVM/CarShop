package com.example.carshop.Vaadin.Car;

import com.example.carshop.App.Car.CarDto;
import com.example.carshop.App.Car.CarService;
import com.example.carshop.Vaadin.ButtonReturn;
import com.vaadin.componentfactory.pdfviewer.PdfViewer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.grid.Grid;
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
        carGrid.addColumn(new ComponentRenderer<>(this::fileReader)).setHeader("Pho");


        Button prevButton = new Button("Poprzednia strona", e -> searchBySerialNumber(-1));
        Button nextButton = new Button("Nastepna strona", e -> searchBySerialNumber(1));

        HorizontalLayout navigationLayout = new HorizontalLayout(prevButton, nextButton);
        ButtonReturn buttonReturn = new ButtonReturn();
        buttonReturn.returnToIndex();

        add(serialNumberField, searchButton, carGrid,navigationLayout, buttonReturn);



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



    private Component fileReader(CarDto carDto) {
        byte[] photoDto = carDto.getPhotoDto();
        String fileType = fileTyp(photoDto);

        if (fileType.equals("image")) {

            Image image = new Image();

                StreamResource resource = new StreamResource("jpg", () -> new ByteArrayInputStream(photoDto));
                image.setSrc(resource);
                image.setWidth("150px");
            image.setHeight("150px");
            return image;

        } else if (fileType.equals("pdf")) {

            PdfViewer pdfViewer = new PdfViewer();
            StreamResource resource = new StreamResource("pdf", () -> new ByteArrayInputStream(photoDto));
            pdfViewer.setSrc(resource);
            pdfViewer.setWidth("150px");
            pdfViewer.setHeight("150px");
            return pdfViewer;


        } else if (fileType.equals("txt")) {

            TextArea textArea = new TextArea();
            textArea.setValue(new String(photoDto, StandardCharsets.UTF_8));
            textArea.setWidth("150px");
            textArea.setHeight("150px");
            return textArea;
        } else {

            return new Span("Nieobsługiwany rodzaj pliku");
        }
    }
    private String fileTyp(byte[] photoByte){
        String fileType = "unknown file type";
        if ((photoByte[0] == (byte) 0xFF) && (photoByte[1] == (byte) 0xD8)) {
            fileType = "image";

         } else if ((photoByte[0] == (byte) 0x25) && (photoByte[1] == (byte) 0x50) &&
            (photoByte[2] == (byte) 0x44) && (photoByte[3] == (byte) 0x46)) {
            fileType = "pdf";

        } else if ((photoByte[0] >= 0x20 && photoByte[0] <= 0x7E) &&
            (photoByte[1] >= 0x20 && photoByte[1] <= 0x7E)) {
            fileType = "txt";
        }
        return fileType;
    }


}
