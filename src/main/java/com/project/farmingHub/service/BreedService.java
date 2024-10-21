package com.project.farmingHub.service;


import com.project.farmingHub.domain.Breed;
import com.project.farmingHub.model.Breed.BreedDto;
import com.project.farmingHub.model.Breed.BreedAndProductDto;
import com.project.farmingHub.model.Breed.BreedFetchDto;
import org.springframework.data.domain.Page;


public interface BreedService {

    Breed addBreeds(BreedDto breed);

    BreedAndProductDto getBreedById(Long id);

    BreedAndProductDto getBreedByIdApproach2(Long id);

    Page<BreedFetchDto> getAllBreeds(int pageNo , int pageSize , String searchKeyword);

    Page<BreedFetchDto> getAllBreedAndProducts(int pageNo , int pageSize , String searchKeyword);
}
