package com.example.carshop.App.Car;




import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
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
    ResponseEntity<?> save(@RequestBody CarDto part ) throws IOException {



        CarDto save = service.save(part);
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

