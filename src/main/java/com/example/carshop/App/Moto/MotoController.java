package com.example.carshop.App.Moto;



import com.example.carshop.App.Exception.SecurityException;
import org.springframework.http.ResponseEntity;


import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;

import java.util.Set;

import static java.io.File.createTempFile;

@RestController
@RequestMapping("/moto")
public class MotoController {
    private final MotoService service;


    public MotoController(MotoService service) {
        this.service = service;

    }
    @PostMapping("/newPart")


    ResponseEntity<MotoDto> save(@RequestParam String mark, @RequestParam String model, @RequestParam String serialNumber,
    @RequestParam String partBrands, @RequestParam BigDecimal price, @RequestParam int quantity,
    @RequestParam String category, @RequestParam("file") MultipartFile file) {

        MotoDto dto = service.saveParam(mark, model, serialNumber, partBrands, price, quantity, category, file);
        MotoDto save = service.save(dto);

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
    ResponseEntity<Set<MotoDto>>findAllBySerialNumber(@RequestParam (required = false) String serialNumber,
                                                     @RequestParam(defaultValue = "0") int page){
        if (serialNumber==null){

            return   ResponseEntity.ok(service.findAll(page));
        }
        return ResponseEntity.ok(service.findAllBySerialNumber(serialNumber,page));
    }
    @PostMapping("/sell")
    ResponseEntity<?> sellPart(@RequestParam String serialNumber, @RequestParam int quantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication instanceof AnonymousAuthenticationToken)) {
            throw new SecurityException();
        }
        String email = authentication.getName();
        service.sellParts(serialNumber, quantity,email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filetyp/{serialNumber}")
    String fileTyp(@PathVariable String serialNumber) {
        MotoDto dto = service.findBySerialNumber(serialNumber).orElseThrow();
        return service.fileTyp(dto.getPhotoDto());

    }

    @PostMapping("/tmp")
    public ResponseEntity<String> saveTempFile(@RequestParam("file") MultipartFile file) {
        File tempFile;
        try {
            tempFile = createTempFile("/tmp", ".pdf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(var fos =  new FileOutputStream(tempFile)) {

            fos.write(file.getBytes());
            return ResponseEntity.ok(tempFile.getAbsolutePath());
        } catch (IOException e) {
            e.getCause();
            return ResponseEntity.status(500).body("Błąd podczas zapisywania pliku tymczasowego");
        }
    }



    }
