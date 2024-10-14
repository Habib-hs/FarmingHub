package com.project.farmingHub.service;

import com.project.farmingHub.entity.Breed;
import org.springframework.http.ResponseEntity;

public interface BreedService {

    ResponseEntity<Breed> addBreeds(Breed breed);
}
