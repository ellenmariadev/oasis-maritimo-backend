package com.example.oasismaritimo.controllers;

import com.example.oasismaritimo.domain.model.Specie;
import com.example.oasismaritimo.services.SpecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/species")
public class SpecieController {

    @Autowired
    private SpecieService specieService;

    @PostMapping
    public ResponseEntity<Specie> createSpecie(@RequestBody Specie specie) {
        Specie createdSpecie = specieService.createSpecie(specie);
        return new ResponseEntity<>(createdSpecie, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSpecie(@PathVariable UUID id) {
        specieService.deleteSpecie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specie> getSpecieById(@PathVariable UUID id) {
        Specie specie = specieService.getSpecieById(id);
        return new ResponseEntity<>(specie, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Specie>> getAllSpecies() {
        List<Specie> species = specieService.getAllSpecies();
        return new ResponseEntity<>(species, HttpStatus.OK);
    }
}