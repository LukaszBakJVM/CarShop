package com.example.carshop.Vaadin.Car;

import com.example.carshop.App.Car.Category.Category;
import com.example.carshop.App.Car.Category.CategoryService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;



@Route("category")
public class NewCategory extends VerticalLayout {
    private final CategoryService categoryService;
  private   TextField category;

    public NewCategory(CategoryService categoryService) {
        this.categoryService = categoryService;
        FormLayout formLayout =new FormLayout();
        category = new TextField("Dodaj kategorie");
        Button save = new Button("Zapisz");
        formLayout.add(category,save);
        save.addClickListener(e->saveCategory());
        add(formLayout);
    }

    private void saveCategory() {
        String value = category.getValue();
        if (category.isEmpty()){
            Notification.show("Please fill in all fields");
        }else {
            Category category =new Category();
            category.setName(value);
            categoryService.newCategory(category);
            if (category!=null){
                Notification.show("Zapisano");
            }else {
                Notification.show("Bład zapisu");
            }
        }

    }
}
