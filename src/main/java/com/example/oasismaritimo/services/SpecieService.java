package com.example.oasismaritimo.services;

import com.example.oasismaritimo.domain.model.Specie;
import com.example.oasismaritimo.repositories.SpecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SpecieService {
    @Autowired
    private SpecieRepository specieRepository;

    public Specie getSpecieById(UUID id) {
        return specieRepository.findById(id).orElse(null);
    }

    public Specie createSpecie(Specie specie) {
        specie.setId(UUID.randomUUID());
        return specieRepository.save(specie);
    }

    public void deleteSpecie(UUID id) {
        specieRepository.deleteById(id);
    }

    public List<Specie> getAllSpecies() {
        return specieRepository.findAll();
    }
}
