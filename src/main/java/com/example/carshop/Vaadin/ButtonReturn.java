package com.example.carshop.Vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public  class ButtonReturn extends VerticalLayout {
    public ButtonReturn() {


    }
    public void returnToIndex(){
        Button addButton = new Button("Powrot");
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        RouterLink link = new RouterLink("", Index.class);
        link.add(addButton);
        add(link);
    }

}
