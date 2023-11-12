package com.example.carshop.App.Car;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


interface CarRepository  extends JpaRepository<Car,Long> {
Optional<Car>findBySerialnumber(String serialNumber);

}
