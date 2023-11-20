package com.example.carshop.Vaadin;


import com.example.carshop.Vaadin.Car.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")

public class Index extends VerticalLayout {

    public Index() {

        Button findAllParts = new Button("Znajdz częśc  samochodową");
        findAllParts.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        RouterLink allLink = new RouterLink("", FindAllParts.class);
        allLink.add(findAllParts);

        Button addCarPart = new Button("Dodaj częśc  samochodową");
        addCarPart.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        RouterLink addCarPartLink = new RouterLink("", AddNewPart.class);
        addCarPartLink.add(addCarPart);

        Button addNewCategory = new Button("Dodaj kategorie części");
        addNewCategory.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        RouterLink categoryLink = new RouterLink("", NewCategory.class);
        categoryLink.add(addNewCategory);

        Button sellPart = new Button("Sprzedaz  części samochodowej");
        sellPart.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        RouterLink sellLink = new RouterLink("", SellPart.class);
        sellLink.add(sellPart);

        Button deleteCarPart = new Button("Usuń część  samochodową");
        deleteCarPart.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        RouterLink deleteCarPartLink = new RouterLink("", DeletePart.class);
        deleteCarPartLink.add(deleteCarPart);


        add(allLink,addCarPartLink,categoryLink,sellLink,deleteCarPartLink);



    }

}
