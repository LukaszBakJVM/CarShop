package com.example.carshop.App.Moto;

import com.example.carshop.App.Car.Category.Category;
import com.example.carshop.App.Shop.ShoppingCart;
import com.example.carshop.App.SuperClass.Parts;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class MotoParts extends Parts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne()
    private Category category;

    @ManyToMany(mappedBy = "motoParts")
    private Set<ShoppingCart>shoppingCarts;

    public MotoParts() {
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

    public Set<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }
}
