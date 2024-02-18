package com.example.oasismaritimo.repositories;

import com.example.oasismaritimo.domain.model.Specie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpecieRepository extends JpaRepository<Specie, UUID> { }