package com.example.carshop.App.Moto;

import com.example.carshop.App.SuperClass.Parts;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MotoParts extends Parts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public MotoParts() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
