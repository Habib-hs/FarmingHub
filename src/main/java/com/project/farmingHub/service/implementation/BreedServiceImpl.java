package com.project.farmingHub.service.implementation;

import com.project.farmingHub.entity.Breed;
import com.project.farmingHub.repo.BreedRepository;
import com.project.farmingHub.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BreedServiceImpl implements BreedService {

    @Autowired
    private BreedRepository breedRepo;


    @Override
    public ResponseEntity<Breed> addBreeds(Breed breed) {
        return null;
    }
}
