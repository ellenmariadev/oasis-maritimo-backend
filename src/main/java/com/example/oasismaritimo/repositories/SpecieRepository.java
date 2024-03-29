package com.example.oasismaritimo.repositories;

import com.example.oasismaritimo.domain.model.Specie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpecieRepository extends JpaRepository<Specie, UUID> { }