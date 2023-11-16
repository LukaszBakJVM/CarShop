package com.example.carshop.Vaadin.Moto;

import com.example.carshop.App.Car.Category.CategoryService;
import com.example.carshop.App.Moto.MotoDto;
import com.example.carshop.App.Moto.MotoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("moto/newpart")

public class AddNewPart extends VerticalLayout {
    private final MotoService motoService;
    private final CategoryService categoryService;
    private TextField brand;
    private TextField model;
    private TextField serialnumber;
    private TextField partsBrand;
    private TextField price;
    private TextField quantity;

    private ComboBox<String> categories;

    public AddNewPart(MotoService motoService, CategoryService categoryService) {
        this.motoService = motoService;
        this.categoryService = categoryService;
        FormLayout formLayout = new FormLayout();
        brand = new TextField("Marka");
        model = new TextField("Model");
        serialnumber = new TextField("Numer Seryjny");
        partsBrand = new TextField("Marka części");
        price = new TextField("Cena");
        quantity = new TextField("Stan magazynowy");
        categories = new ComboBox<>("Kategoria");
        categories.setItems(categoryService.findAll());


        Button saveButton = new Button("Zapisz");
        formLayout.add(brand, model, serialnumber, partsBrand, price, quantity, categories, saveButton);
        saveButton.addClickListener(e -> saveMotoParts());

        add(formLayout);
    }

    private void saveMotoParts() {
        String value = brand.getValue();
        String value1 = model.getValue();
        String value2 = serialnumber.getValue();
        String value3 = partsBrand.getValue();
        String value4 = price.getValue();
        String value5 = quantity.getValue();
        String category = categories.getValue();

        if (brand.isEmpty() || model.isEmpty() || category == null) {
            Notification.show("Uzupełnij wszystkie pola");
        } else {
            MotoDto motoDto = new MotoDto();
            motoDto.setMark(value);
            motoDto.setModel(value1);
            motoDto.setSerialNumber(value2);
            motoDto.setPartsBrand(value3);
            motoDto.setPrice(value4);
            motoDto.setQuantity(Integer.parseInt(value5));
            motoDto.setCategory(category);
            MotoDto saveMoto = motoService.save(motoDto);
            if (saveMoto != null) {
                Notification.show("Zapisano");

            } else {
                Notification.show("Błąd zapisu");
            }

        }
    }
}


