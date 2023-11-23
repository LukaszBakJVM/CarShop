package com.example.carshop.App.Car;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;



interface CarRepository  extends JpaRepository<Car,Long> {
Optional<Car>findBySerialnumber(String serialNumber);
Page<Car>findCarBySerialnumberContainingIgnoreCase(String serialNumber,Pageable pageable);
Page<Car>findAll(Pageable pageable);




}
