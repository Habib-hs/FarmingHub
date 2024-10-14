package com.project.farmingHub.service.implementation;

import com.project.farmingHub.entity.HealthProduct;
import com.project.farmingHub.exception.HealthProductServiceException;
import com.project.farmingHub.repo.HealthProductRepository;
import com.project.farmingHub.service.HealthProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HealthProductServiceImpl implements HealthProductService {

    private final HealthProductRepository healthProductRepository;

    public HealthProductServiceImpl(HealthProductRepository healthProductRepository) {
        this.healthProductRepository = healthProductRepository;
    }

    @Override
    public HealthProduct addProducts(HealthProduct healthProduct) throws HealthProductServiceException {

        if(healthProductRepository.existsByProductName(healthProduct.getProductName())){
            //throw new HealthProductServiceException("Product name already exists");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Product name already exists");
        }

        HealthProduct product = new HealthProduct();

        product.setProductName(healthProduct.getProductName());

        product.setProductType(healthProduct.getProductType());


        return healthProductRepository.save(product);
    }
}
