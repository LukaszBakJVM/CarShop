package com.example.carshop.App.Car;


import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.Set;


interface CarRepository  extends JpaRepository<Car,Long> {
Optional<Car>findBySerialnumber(String serialNumber);
Set<Car>findCarBySerialnumberContainingIgnoreCase(String serialNumber);



}
