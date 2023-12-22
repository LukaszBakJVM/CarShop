package com.example.carshop.App.Shop;

import com.example.carshop.App.LoginAndRegistration.Person;
import com.example.carshop.App.Shop.Basket.CarParts.CarPartsBasket;
import com.example.carshop.App.Shop.Basket.MotoParts.MotoPartsBasket;
import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Person person;
    @ManyToMany
    @JoinTable(
            name = "shop_car_part",
            joinColumns = @JoinColumn(name = "Shoping_car_part_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "car_parts_id", referencedColumnName = "id"))
    private Set<CarPartsBasket>carsParts =new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "shop_moto_part",
            joinColumns = @JoinColumn(name = "Shoping_moto_part_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "moto_parts_id", referencedColumnName = "id"))
    private Set<MotoPartsBasket>motoParts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<CarPartsBasket> getCarsParts() {
        return carsParts;
    }

    public void setCarsParts(Set<CarPartsBasket> carsParts) {
        this.carsParts = carsParts;
    }

    public Set<MotoPartsBasket> getMotoParts() {
        return motoParts;
    }

    public void setMotoParts(Set<MotoPartsBasket> motoParts) {
        this.motoParts = motoParts;
    }
}
