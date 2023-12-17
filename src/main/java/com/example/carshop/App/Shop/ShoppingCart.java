package com.example.carshop.App.Shop;

import com.example.carshop.App.Car.Car;
import com.example.carshop.App.LoginAndRegistration.Person;
import com.example.carshop.App.Moto.MotoParts;
import jakarta.persistence.*;

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
            joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id"))
   private Set<Car>carsParts;
    @ManyToMany
    @JoinTable(
            name = "shop_moto_part",
            joinColumns = @JoinColumn(name = "moto_part_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id"))
    private Set<MotoParts>motoParts;

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

    public Set<Car> getCarsParts() {
        return carsParts;
    }

    public void setCarsParts(Set<Car> carsParts) {
        this.carsParts = carsParts;
    }

    public Set<MotoParts> getMotoParts() {
        return motoParts;
    }

    public void setMotoParts(Set<MotoParts> motoParts) {
        this.motoParts = motoParts;
    }
}
