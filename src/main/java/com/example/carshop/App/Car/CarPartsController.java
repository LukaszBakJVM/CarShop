package com.example.carshop.App.Car;




import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/car")

public class CarPartsController {
    private final CarService service;


    public CarPartsController(CarService service) {
        this.service = service;

    }

    @PostMapping( "/newPart")
    ResponseEntity<?> save(@RequestParam String mark, @RequestParam String model,@RequestParam String serialNumber,
        @RequestParam String partBrands,@RequestParam BigDecimal price,@RequestParam int quantity ,
                           @RequestParam String category,@RequestParam ("file") MultipartFile file){

        CarDto carDto = service.saveParam(mark, model, serialNumber, partBrands, price, quantity, category, file);



        CarDto save = service.save(carDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(save.getId()).toUri();

        return ResponseEntity.created(uri).body(save);
    }
    @DeleteMapping("/{serialNumber}")
    ResponseEntity<?>deleteBySerialNumber(@PathVariable String serialNumber){
        service.delete(serialNumber);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("")
    ResponseEntity<Set<CarDto>>findAllBySerialNumber(@RequestParam (required = false) String serialNumber,
      @RequestParam(defaultValue = "0") int page){
        if (serialNumber==null){

            return   ResponseEntity.ok(service.findAll(page));
        }
        return ResponseEntity.ok(service.findAllBySerialNumber(serialNumber,page));
    }
    @PatchMapping("/sell")
    ResponseEntity<?> sellPart(@RequestParam String serialNumber,@RequestParam int quantity) {
      service.sellParts(serialNumber,quantity);
      return ResponseEntity.noContent().build();
    }

    @GetMapping("/{serialNumber}")
    ResponseEntity<Optional<CarDto>>findBySerialNumber(@PathVariable String serialNumber){
       return ResponseEntity.ok(service.findBySerialNumber(serialNumber));
    }


}

