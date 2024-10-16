package com.project.farmingHub.controller;

import com.project.farmingHub.entity.HealthProduct;
import com.project.farmingHub.exception.HealthProductServiceException;
import com.project.farmingHub.model.HealthProductDto;
import com.project.farmingHub.service.HealthProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
public class HealthProductController {

    private final HealthProductService healthProductService;

    public HealthProductController(HealthProductService healthProductService) {
        this.healthProductService = healthProductService;
    }


    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody @Validated HealthProduct healthProduct) throws HealthProductServiceException {
        if(healthProductService.addProducts(healthProduct) != null){
            return ResponseEntity.ok("Product inserted successfully");
        }else{
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product failed to insert");
        }
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<HealthProductDto> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(healthProductService.getProductById(id));
    }


    @GetMapping("/get/productList")
    public ResponseEntity<?> getAllProducts(@RequestParam(defaultValue = "0") int pageNo,
                                            @RequestParam(defaultValue = "5") int pageSize,
                                            @RequestParam(required = false)String searchKeyword) {
        return ResponseEntity.ok(healthProductService.getAllProducts(pageNo , pageSize , searchKeyword));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@Valid @RequestBody HealthProductDto healthProductDto,
                                        @RequestParam Long id){
         if(healthProductService.updateProduct(healthProductDto , id) != null){
              return ResponseEntity.ok("Product updated successfully");
         }else{
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product updated failed");
         }
    }



}
