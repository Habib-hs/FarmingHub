package com.project.farmingHub.service;

import com.project.farmingHub.entity.HealthProduct;
import com.project.farmingHub.exception.HealthProductServiceException;

public interface HealthProductService {

    HealthProduct addProducts(HealthProduct healthProduct) throws HealthProductServiceException;
}
