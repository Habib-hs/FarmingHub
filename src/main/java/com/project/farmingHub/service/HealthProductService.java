package com.project.farmingHub.service;

import com.project.farmingHub.entity.HealthProduct;
import com.project.farmingHub.exception.HealthProductServiceException;
import com.project.farmingHub.model.HealthProductDto;
import org.springframework.data.domain.Page;



public interface HealthProductService {

    HealthProduct addProducts(HealthProduct healthProduct) throws HealthProductServiceException;

    HealthProductDto getProductById(Long id);

    Page<HealthProductDto> getAllProducts(int pageNo , int pageSize , String searchKeyword);

    HealthProductDto updateProduct(HealthProductDto dto , Long id);
}
