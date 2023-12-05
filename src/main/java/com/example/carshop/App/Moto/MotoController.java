package com.example.carshop.App.Moto;


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
@RequestMapping("/moto")
public class MotoController {
    private final MotoService service;
    private final ObjectMapper objectMapper;

    public MotoController(MotoService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }
    @PostMapping("/newPart")
    ResponseEntity<MotoDto> save(@RequestBody MotoDto part) {
        MotoDto save = service.save(part);
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
    @PatchMapping("/{serialNumber}/{quantity}")
    ResponseEntity<?> sellPart(@PathVariable String serialNumber,@PathVariable int quantity,
                               @RequestBody JsonMergePatch patch) {
        try {

            MotoDto motoDto = service.findBySerialNumber(serialNumber).orElseThrow();
            MotoDto update = applyPatch(motoDto, patch);
            service.sellParts(update.getSerialNumber(),quantity);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private MotoDto applyPatch(MotoDto motoDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode jsonNode = objectMapper.valueToTree(motoDto);

        JsonNode jobOfferPatchedNode = patch.apply(jsonNode);
        return objectMapper.treeToValue(jobOfferPatchedNode, MotoDto.class);
    }

}