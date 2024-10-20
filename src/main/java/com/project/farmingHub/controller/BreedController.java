package com.project.farmingHub.controller;
import com.project.farmingHub.exception.BreedServiceException;
import com.project.farmingHub.model.Breed.BreedDto;
import com.project.farmingHub.model.Breed.BreedGetDto;
import com.project.farmingHub.service.BreedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/breed")
public class BreedController {

    private final BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody @Validated BreedDto breed) throws BreedServiceException {
        if(breedService.addBreeds(breed) != null){
            return ResponseEntity.ok("Breed inserted successfully");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Breed failed to insert");
        }
    }


    @GetMapping(value = "/get/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BreedGetDto> getBreedById(@PathVariable Long id){
        return ResponseEntity.ok(breedService.getBreedById(id));
    }


}
