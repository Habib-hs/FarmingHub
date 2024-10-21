package com.project.farmingHub.service.implementation;

import com.project.farmingHub.domain.Breed;
import com.project.farmingHub.domain.HealthProduct;
import com.project.farmingHub.exception.BreedServiceException;
import com.project.farmingHub.exception.HealthProductServiceException;
import com.project.farmingHub.model.Breed.BreedAndProductDto;
import com.project.farmingHub.model.Breed.BreedDto;
import com.project.farmingHub.model.Breed.BreedFetchDto;
import com.project.farmingHub.model.HealthProduct.HealthProductDto;
import com.project.farmingHub.repo.BreedRepository;
import com.project.farmingHub.repo.HealthProductRepository;
import com.project.farmingHub.service.BreedService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public BreedAndProductDto getBreedById(Long id) {
        return breedRepo.findById(id)
                .map(breed -> BreedAndProductDto.builder()
                        .breedId(breed.getBreedId())
                        .breedName(breed.getBreedName())
                        .healthProductDtoList(breed.getRecommendedProducts().stream()
                                .map(healthProduct -> HealthProductDto.builder()
                                        .id(healthProduct.getProductId())
                                        .productName(healthProduct.getProductName())
                                        .productType(healthProduct.getProductType())
                                        .build()).collect(Collectors.toSet()))
                        .build())
                .orElseThrow(()-> new BreedServiceException("Breed with " + id + " id not found"));
    }



    @Override
    public BreedAndProductDto getBreedByIdApproach2(Long breedId) {
        Breed breed = breedRepo.findById(breedId)
                .orElseThrow(() -> new BreedServiceException("Breed not found with id: " + breedId));

        //System.out.println("Number of recommended products: " + breed.getRecommendedProducts().size());

        return mapToDTO(breed);
    }
    private BreedAndProductDto mapToDTO(Breed breed) {
        BreedAndProductDto breedDTO = new BreedAndProductDto();
        breedDTO.setBreedId(breed.getBreedId());
        breedDTO.setBreedName(breed.getBreedName());

        Set<HealthProductDto> healthProductDTOs = breed.getRecommendedProducts().stream()
                .map(this::mapToHealthProductDTO)
                .collect(Collectors.toSet());

       // System.out.println("Number of products mapped: " + healthProductDTOs.size());

        breedDTO.setHealthProductDtoList(healthProductDTOs);
        return breedDTO;
    }

    private HealthProductDto mapToHealthProductDTO(HealthProduct product) {
        HealthProductDto dto = new HealthProductDto();
        dto.setId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setProductType(product.getProductType());
        return dto;
    }



    @Override
    public Page<BreedFetchDto> getAllBreeds(int pageNo, int pageSize, String searchKeyword) {
        Pageable pageable = PageRequest.of(pageNo , pageSize);

        if(searchKeyword == null || searchKeyword.isEmpty()){
            Page<Breed> breeds= breedRepo.findAll(pageable);

            if(breeds.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND , "No data found");
            }

            return breeds.map(it->
                    new BreedFetchDto(it.getBreedId() , it.getBreedName())
            );
        }else{
            Page<Breed> breeds= breedRepo.findAllByKeyword(searchKeyword , pageable);

            if(breeds.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND , "No data found");
            }

            return breeds.map(it->
                    new BreedFetchDto(it.getBreedId() , it.getBreedName())
            );
        }
    }

    @Override
    public Page<BreedFetchDto> getAllBreedAndProducts(int pageNo, int pageSize, String searchKeyword) {
        return null;
    }

}
