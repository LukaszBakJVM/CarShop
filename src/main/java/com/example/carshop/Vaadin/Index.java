package com.example.carshop.Vaadin;

import com.example.carshop.Vaadin.Car.AddNewPart;
import com.example.carshop.Vaadin.Car.FindAllParts;
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
        RouterLink all = new RouterLink("", FindAllParts.class);
        all.add(findAllParts);


        Button addButton = new Button("Dodaj częśc  samochodową");
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        RouterLink link = new RouterLink("", AddNewPart.class);
        link.add(addButton);


        add(all,link);


    }

}
