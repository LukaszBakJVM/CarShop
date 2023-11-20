package com.example.carshop.Vaadin;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
@CssImport("./styles/shared-styles.css")

public  class ButtonReturn extends VerticalLayout {
    public ButtonReturn() {


    }
    public void returnToIndex(){

        Button addButton = new Button("Powrot");
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        RouterLink link = new RouterLink("", Index.class);
        link.add(addButton);
        add(link);
    }

}
