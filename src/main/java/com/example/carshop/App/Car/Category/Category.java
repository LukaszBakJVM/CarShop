package com.example.carshop.App.Car.Category;

import com.example.carshop.App.Car.Car;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {
    @Id
    @Column(unique = true)
    private String name;
    @OneToMany
    private Set<Car>cars=new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String category) {
        this.name = category;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
