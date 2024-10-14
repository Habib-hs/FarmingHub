package com.project.farmingHub.controller;

import com.project.farmingHub.entity.HealthProduct;
import com.project.farmingHub.exception.HealthProductServiceException;
import com.project.farmingHub.service.HealthProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
