package com.example.carshop.Vaadin;


import com.example.carshop.Vaadin.Car.*;
import com.example.carshop.Vaadin.Moto.AddNewMotoPart;
import com.example.carshop.Vaadin.Moto.DeleteMotoPart;
import com.example.carshop.Vaadin.Moto.FindAllMotoParts;
import com.example.carshop.Vaadin.Moto.SellMotoPart;
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
        RouterLink allLink = new RouterLink("", FindAllCarParts.class);
        allLink.add(findAllParts);

        Button addCarPart = new Button("Dodaj częśc  samochodową");
        addCarPart.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        RouterLink addCarPartLink = new RouterLink("", AddNewCarPart.class);
        addCarPartLink.add(addCarPart);

        Button addNewCategory = new Button("Dodaj kategorie części");
        addNewCategory.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        RouterLink categoryLink = new RouterLink("", NewCategory.class);
        categoryLink.add(addNewCategory);

        Button sellPart = new Button("Sprzedaz  części samochodowej");
        sellPart.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        RouterLink sellLink = new RouterLink("", SellCarPart.class);
        sellLink.add(sellPart);

        Button deleteCarPart = new Button("Usuń część  samochodową");
        deleteCarPart.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        RouterLink deleteCarPartLink = new RouterLink("", DeleteCarPart.class);
        deleteCarPartLink.add(deleteCarPart);

        Button findAllMotoParts = new Button("Znajdz częśc  motocyklowa");
        findAllMotoParts.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        RouterLink findAllMotoPartsLink = new RouterLink("", FindAllMotoParts.class);
        findAllMotoPartsLink.add(findAllMotoParts);


        Button addMotoPart = new Button("Dodaj częśc motocyklowa");
        addMotoPart.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        RouterLink addMotoPartLink = new RouterLink("", AddNewMotoPart.class);
        addMotoPartLink.add(addMotoPart);

        Button sellMotoPart = new Button("Sprzedaz  części motocyklowa");
        sellMotoPart.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        RouterLink sellMotoPartLink = new RouterLink("", SellMotoPart.class);
        sellMotoPartLink.add(sellMotoPart);

        Button deleteMotoPart = new Button("Usuń część  motocyklowa");
        deleteMotoPart.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        RouterLink deleteMotoPartLink = new RouterLink("", DeleteMotoPart.class);
        deleteMotoPartLink.add(deleteMotoPart);

        add(allLink,addCarPartLink,categoryLink,sellLink,deleteCarPartLink,findAllMotoPartsLink,
                addMotoPartLink,sellMotoPartLink,deleteMotoPartLink);



    }

}
