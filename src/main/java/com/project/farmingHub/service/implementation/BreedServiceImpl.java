package com.project.farmingHub.service.implementation;

import com.project.farmingHub.entity.Breed;
import com.project.farmingHub.entity.HealthProduct;
import com.project.farmingHub.exception.BreedServiceException;
import com.project.farmingHub.exception.HealthProductServiceException;
import com.project.farmingHub.model.Breed.BreedDto;
import com.project.farmingHub.model.Breed.BreedGetDto;
import com.project.farmingHub.model.HealthProductDto;
import com.project.farmingHub.repo.BreedRepository;
import com.project.farmingHub.repo.HealthProductRepository;
import com.project.farmingHub.service.BreedService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public BreedGetDto getBreedById(Long id) {
        return breedRepo.findById(id)
                .map(breed -> BreedGetDto.builder()
                        .breedId(breed.getBreedId())
                        .breedName(breed.getBreedName())
                        .healthProductDtoList(breed.getRecommendedProducts().stream()
                                .map(healthProduct -> HealthProductDto.builder()
                                        .id(healthProduct.getProductId())
                                        .productName(healthProduct.getProductName())
                                        .productType(healthProduct.getProductType())
                                        .build()).collect(Collectors.toList()))
                        .build())
                .orElseThrow(()-> new BreedServiceException("Breed with " + id + " id not found"));
    }
}
