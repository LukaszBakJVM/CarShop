package com.example.carshop.App.Car;


import org.springframework.data.jpa.repository.JpaRepository;



interface CarRepository  extends JpaRepository<Long,Car> {

}
