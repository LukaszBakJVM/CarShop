package com.example.carshop.Vaadin.Car;

import com.example.carshop.App.Car.CarDto;
import com.example.carshop.App.Car.CarService;
import com.example.carshop.App.Car.Category.CategoryService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;



@Route("car")
public class AddNewCar extends VerticalLayout {

    private final CarService carService;
    private final CategoryService categoryService;
    private TextField brand;
    private TextField model;
    private TextField serialnumber;
    private TextField partsBrand;
    private TextField price;
    private TextField quantity;
 //   private TextField category;
    private ComboBox<String> categories;

    public AddNewCar(CarService carService, CategoryService categoryService) {
        this.carService = carService;
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
        saveButton.addClickListener(e -> saveCar());


        add(formLayout);
    }

    private void saveCar() {
        String value = brand.getValue();
        String value1 = model.getValue();
        String value2 = serialnumber.getValue();
        String value3 = partsBrand.getValue();
        String value4 = price.getValue();
        String value5 = quantity.getValue();
        String category = categories.getValue();

        if (brand.isEmpty() || model.isEmpty() || category == null) {
            Notification.show("Please fill in all fields");
        } else {
            CarDto carDto = new CarDto();
            carDto.setMark(value);
            carDto.setModel(value1);
            carDto.setSerialNumber(value2);
            carDto.setPartsBrand(value3);
            carDto.setPrice(value4);
            carDto.setQuantity(Integer.parseInt(value5));
            carDto.setCategory(category);

            CarDto savedCar = carService.save(carDto);

            if (savedCar != null) {
                Notification.show("Car saved successfully");

            } else {
                Notification.show("Failed to save car");
            }

        }
    }


}