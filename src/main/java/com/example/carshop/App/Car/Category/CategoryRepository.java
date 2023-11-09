package com.example.carshop.App.Car.Category;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface CategoryRepository extends CrudRepository<String,Category> {


    Optional<Category> findById(String category);
}
