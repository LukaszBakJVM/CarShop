package com.example.carshop.App.Car;

import com.example.carshop.App.Car.Category.Category;
import com.example.carshop.App.Shop.ShoppingCart;
import com.example.carshop.App.SuperClass.Parts;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Car extends Parts  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    private Category category;

    @ManyToMany(mappedBy = "carsParts")
    private List<ShoppingCart>shoppingCarts;



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

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }
}
