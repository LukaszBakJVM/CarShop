package com.example.carshop.App.Car;

import com.example.carshop.App.Car.Category.Category;
import com.example.carshop.App.SuperClass.Parts;
import jakarta.persistence.*;

@Entity
public class Car extends Parts  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    private Category category;



    public Car() {

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
