package com.project.farmingHub.service.implementation;

import com.project.farmingHub.entity.Breed;
import com.project.farmingHub.entity.HealthProduct;
import com.project.farmingHub.exception.BreedServiceException;
import com.project.farmingHub.exception.HealthProductServiceException;
import com.project.farmingHub.model.BreedDto;
import com.project.farmingHub.repo.BreedRepository;
import com.project.farmingHub.repo.HealthProductRepository;
import com.project.farmingHub.service.BreedService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BreedServiceImpl implements BreedService {

    @Autowired
    private BreedRepository breedRepo;

    @Autowired
    private HealthProductRepository healthProductRepository;


    @Transactional
    @Override
    public Breed addBreeds(BreedDto breed) {

        Set<HealthProduct> healthProductSet = new HashSet<>();

        breed.getProductSet().forEach(
                healthProduct -> healthProductSet.add(healthProductRepository.findByProductName(healthProduct)
                        .orElseThrow(()-> new HealthProductServiceException("Product with name " + healthProduct + " does not exist")))
        );

        if(breedRepo.existsByBreedName(breed.getBreedName())){
            throw new BreedServiceException("Breed with name " +breed.getBreedName() + " already exists");
        }

        Breed ob =  Breed.builder()
                .breedName(breed.getBreedName())
                .recommendedProducts(healthProductSet)
                .build();

        return breedRepo.save(ob);
    }
}
