package com.project.farmingHub.controller;
import com.project.farmingHub.exception.BreedServiceException;
import com.project.farmingHub.model.Breed.BreedAndProductDto;
import com.project.farmingHub.model.Breed.BreedDto;
import com.project.farmingHub.service.BreedService;
import org.springframework.http.HttpStatus;
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


    @GetMapping(value = "/get/{id}")
    public ResponseEntity<BreedAndProductDto> getBreedById(@PathVariable Long id){
        return ResponseEntity.ok(breedService.getBreedById(id));
    }

    //also add produces inside the get mapping  -> produces = MediaType.APPLICATION_JSON_VALUE
    @GetMapping(value = "/get/approach/{id}")
    public ResponseEntity<BreedAndProductDto> getBreedByIdApproach2(@PathVariable Long id){
        return ResponseEntity.ok(breedService.getBreedByIdApproach2(id));
    }

    @GetMapping("/get/breedList")
    public ResponseEntity<?> getAllBreeds(@RequestParam(defaultValue = "0") int pageNo,
                                            @RequestParam(defaultValue = "5") int pageSize,
                                            @RequestParam(required = false)String searchKeyword) {
        return ResponseEntity.ok(breedService.getAllBreeds(pageNo , pageSize , searchKeyword));
    }


    @GetMapping("/get/breed/product/list")
    public ResponseEntity<?> getAllBreedAndProducts(@RequestParam(defaultValue = "0") int pageNo,
                                          @RequestParam(defaultValue = "5") int pageSize,
                                          @RequestParam(required = false)String searchKeyword) {
        return ResponseEntity.ok(breedService.getAllBreedAndProducts(pageNo , pageSize , searchKeyword));
    }


}
