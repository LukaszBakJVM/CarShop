package com.example.carshop.Vaadin.Moto;


import com.example.carshop.App.Moto.MotoDto;
import com.example.carshop.App.Moto.MotoService;
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

@Route("moto/all")
public class FindAllMotoParts extends VerticalLayout {
    private final MotoService motoService;
    private TextField serialNumberField;
    private Grid<MotoDto> carGrid;
    private int currentPage = 0;

    public FindAllMotoParts(MotoService motoService) {
        this.motoService = motoService;

        serialNumberField = new TextField("Wpisz numer seryjny cześci");
        Button searchButton = new Button("Szukaj");
        searchButton.addClickListener(event -> searchBySerialNumber(currentPage));

        carGrid = new Grid<>(MotoDto.class);
        carGrid.setColumns("mark", "model", "serialNumber", "partsBrand", "price", "quantity", "category");
        carGrid.addColumn(new ComponentRenderer<>(this::fileReader)).setHeader("Photo");


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
            Set<MotoDto> moto = findAllCarsBySerialNumber(serialNumber);

            currentPage += direction;
            carGrid.setItems(moto);

        } else {
            Set<MotoDto> all = motoService.findAll(currentPage);

            currentPage += direction;
            carGrid.setItems(all);

        }
    }

    private Set<MotoDto> findAllCarsBySerialNumber(String serialNumber) {
        return motoService.findAllBySerialNumber(serialNumber,currentPage);
    }



    private Component fileReader(MotoDto motoDto) {

        byte[] photoDto = motoDto.getPhotoDto();

        if ((photoDto[0] == (byte) 0xFF) && (photoDto[1] == (byte) 0xD8)) {

            Image image = new Image();
            if (photoDto != null) {
                StreamResource resource = new StreamResource("", () -> new ByteArrayInputStream(photoDto));
                image.setSrc(resource);
            }
            image.setWidth("150px");
            image.setHeight("150px");
            return image;
        } else if ((photoDto[0] == (byte) 0x25) && (photoDto[1] == (byte) 0x50) &&
                (photoDto[2] == (byte) 0x44) && (photoDto[3] == (byte) 0x46)) {

            PdfViewer pdfViewer = new PdfViewer();
            StreamResource resource = new StreamResource("", () -> new ByteArrayInputStream(photoDto));
            pdfViewer.setSrc(resource);
            pdfViewer.setWidth("50px");
            pdfViewer.setHeight("50px");
            return pdfViewer;


        } else if ((photoDto[0] >= 0x20 && photoDto[0] <= 0x7E) &&
                (photoDto[1] >= 0x20 && photoDto[1] <= 0x7E)) {

            TextArea textArea = new TextArea();
            textArea.setValue(new String(photoDto, StandardCharsets.UTF_8));
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


}
