package com.project.farmingHub.service;


import com.project.farmingHub.entity.Breed;
import com.project.farmingHub.model.Breed.BreedDto;
import com.project.farmingHub.model.Breed.BreedGetDto;

public interface BreedService {

    Breed addBreeds(BreedDto breed);

    BreedGetDto getBreedById(Long id);
}
