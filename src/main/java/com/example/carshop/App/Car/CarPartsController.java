package com.example.carshop.App.Car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("/car")

public class CarPartsController {
    private final CarService service;
    private final ObjectMapper objectMapper;

    public CarPartsController(CarService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/newPart")
    ResponseEntity<CarDto> save(@RequestBody CarDto part) {
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
      @RequestParam(defaultValue = "1") int page){
        if (serialNumber==null){

            return   ResponseEntity.ok(service.findAll(page));
        }
        return ResponseEntity.ok(service.findAllBySerialNumber(serialNumber,page));
    }
    @PatchMapping("/sell")
    ResponseEntity<?> sellPart(@RequestParam String serialNumber,@RequestParam int quantity,
                               @RequestBody JsonMergePatch patch) {
        try {

            CarDto carDto = service.findBySerialNumber(serialNumber).orElseThrow();
            CarDto update = applyPatch(carDto, patch);
            service.sellParts(update.getSerialNumber(),quantity);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private CarDto applyPatch(CarDto carDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode jsonNode = objectMapper.valueToTree(carDto);

        JsonNode jobOfferPatchedNode = patch.apply(jsonNode);
        return objectMapper.treeToValue(jobOfferPatchedNode, CarDto.class);
    }


}

