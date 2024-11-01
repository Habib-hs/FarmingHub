package com.project.farmingHub.service;


import com.project.farmingHub.domain.Breed;
import com.project.farmingHub.model.Breed.BreedDto;
import com.project.farmingHub.model.Breed.BreedAndProductDto;
import com.project.farmingHub.model.Breed.BreedFetchDto;
import org.springframework.data.domain.Page;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;


public interface BreedService {

    Breed addBreeds(BreedDto breed);

    BreedAndProductDto getBreedById(Long id);

    BreedAndProductDto getBreedByIdApproach2(Long id);

    Page<BreedFetchDto> getAllBreeds(int pageNo , int pageSize , String searchKeyword);

    Page<BreedAndProductDto> getAllBreedAndProducts(int pageNo , int pageSize , String searchKeyword);

    PagedModel<EntityModel<BreedAndProductDto>> getPaginatedBreeds(int pageNo, int pageSize);
}
