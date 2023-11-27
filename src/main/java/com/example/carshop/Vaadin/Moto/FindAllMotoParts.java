package com.example.carshop.Vaadin.Moto;


import com.example.carshop.App.Moto.MotoDto;
import com.example.carshop.App.Moto.MotoService;
import com.example.carshop.Vaadin.ButtonReturn;
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
        carGrid.addColumn(new ComponentRenderer<>(this::createImageComponent)).setHeader("Photo");

        Set<MotoDto> serialNumber = findAllCarsBySerialNumber("wpisz nr seryjny");
        carGrid.setItems(serialNumber);
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



    private Image createImageComponent(MotoDto motoDto) {
        Image image = new Image();
        if (motoDto.getPhotoDto() != null) {
            StreamResource resource = new StreamResource("image.jpg", () -> new ByteArrayInputStream(motoDto.getPhotoDto()));
            image.setSrc(resource);
        }
        image.setWidth("150px");
        image.setHeight("150px");
        return image;
    }
    private int numberOfPage(int pageSize ,int size) {
        return size/pageSize;


    }


}
