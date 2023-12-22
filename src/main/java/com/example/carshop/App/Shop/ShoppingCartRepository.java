package com.example.carshop.App.Shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

    Optional<ShoppingCart>findByPersonEmail(String email);
    Page<ShoppingCart>findAByPersonEmail(String email,Pageable pageable);

}
