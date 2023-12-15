package com.example.carshop.App.Car.Category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<String>> allCategory() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    ResponseEntity<Category> save(@RequestBody Category category) {
        Category saveCategory = service.newCategory(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(saveCategory.getName()).toUri();
        return ResponseEntity.created(uri).body(saveCategory);
    }


}