package com.example.carshop.App.Car.Category;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
  public   List<String>findAll(){
     return    categoryRepository.findAll()
             .stream()
             .map(Category::getName)
             .sorted().toList();

    }
}
