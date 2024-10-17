package com.project.farmingHub.controller;
import com.project.farmingHub.exception.BreedServiceException;
import com.project.farmingHub.model.BreedDto;
import com.project.farmingHub.service.BreedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
