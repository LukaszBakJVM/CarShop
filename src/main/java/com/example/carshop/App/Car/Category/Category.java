package com.example.carshop.App.Car.Category;

import com.example.carshop.App.Car.Car;
import com.example.carshop.App.Moto.MotoParts;
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
    @OneToMany(mappedBy = "category")
    private Set<Car>cars = new HashSet<>();
    @OneToMany(mappedBy = "category")
    private Set<MotoParts>motoParts = new HashSet<>();

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

    public Set<MotoParts> getMotoParts() {
        return motoParts;
    }

    public void setMotoParts(Set<MotoParts> motoParts) {
        this.motoParts = motoParts;
    }
}


