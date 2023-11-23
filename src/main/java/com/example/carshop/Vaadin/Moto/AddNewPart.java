package com.example.carshop.Vaadin.Moto;

import com.example.carshop.App.Car.Category.CategoryService;
import com.example.carshop.App.Moto.MotoDto;
import com.example.carshop.App.Moto.MotoService;
import com.example.carshop.Vaadin.ButtonReturn;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
    private final MemoryBuffer buffer = new MemoryBuffer();
    private final Upload upload = new Upload(buffer);

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
        upload.addSucceededListener(e-> Notification.show("Zdięcie dodane",3000,Notification.Position.MIDDLE));


        Button saveButton = new Button("Zapisz");
        ButtonReturn buttonReturn = new ButtonReturn();
        buttonReturn.returnToIndex();
        formLayout.add(brand, model, serialnumber, partsBrand, price, quantity, categories,upload, saveButton,buttonReturn);
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
            try {
                InputStream inputStream = buffer.getInputStream();
                byte[] bytes = readBytes(inputStream);
                motoDto.setPhotoDto(bytes);

            MotoDto saveMoto = motoService.save(motoDto);
            if (saveMoto != null) {
                Notification.show("Zapisano");

            } else {
                Notification.show("Błąd zapisu");
            }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

        private byte[] readBytes(InputStream inputStream) throws IOException {
            try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                int nRead;
                byte[] data = new byte[10000000];
                while ((nRead = inputStream.read(data, 0, data.length)) !=-1) {
                    buffer.write(data, 0, nRead);
                }
                return buffer.toByteArray();
            }

        }
}


