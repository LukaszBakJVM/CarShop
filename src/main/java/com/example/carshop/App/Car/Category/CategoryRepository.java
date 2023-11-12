package com.example.carshop.App.Car.Category;

import org.springframework.data.repository.CrudRepository;

import java.util.List;



public interface CategoryRepository extends CrudRepository<Category,String> {
    List<Category>findAll();




}