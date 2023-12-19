package com.example.carshop.App.Shop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart>findAllByPersonId(long id);
    Optional<ShoppingCart>findByPersonEmail(String email);
}
