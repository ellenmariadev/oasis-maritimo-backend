package com.example.oasismaritimo;

import com.example.oasismaritimo.repository.AnimalRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.oasismaritimo.models.Animal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OasisMaritimoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OasisMaritimoApplication.class, args);
	}

	@Bean
	public CommandLineRunner populateDatabase(AnimalRepository animalRepository) {
		return args -> {
			animalRepository.save(new Animal("Dolphin", "Delphinidae", 15));
			animalRepository.save(new Animal("Shark", "Selachimorpha", 20));
			animalRepository.save(new Animal("Whale", "Cetacea", 30));
		};
	}
}