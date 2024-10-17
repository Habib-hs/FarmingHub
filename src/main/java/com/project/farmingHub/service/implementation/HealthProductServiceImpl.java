package com.project.farmingHub.service.implementation;

import com.project.farmingHub.entity.HealthProduct;
import com.project.farmingHub.exception.HealthProductServiceException;
import com.project.farmingHub.model.HealthProductDto;
import com.project.farmingHub.repo.HealthProductRepository;
import com.project.farmingHub.service.HealthProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public HealthProduct addProducts(HealthProduct healthProduct) {

        if(healthProductRepository.existsByProductName(healthProduct.getProductName())){
            //throw new HealthProductServiceException("Product name already exists");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Product name already exists");
        }

        HealthProduct product = new HealthProduct();

        product.setProductName(healthProduct.getProductName());

        product.setProductType(healthProduct.getProductType());


        return healthProductRepository.save(product);
    }

    @Override
    public HealthProductDto getProductById(Long id) {
        return healthProductRepository.findById(id)
                .map(healthProduct -> HealthProductDto.builder()
                        .id(healthProduct.getProductId())
                        .productName(healthProduct.getProductName())
                        .productType(healthProduct.getProductType())
                        .build())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST , "Product with " + id + " id not found"));
    }

    @Override
    public Page<HealthProductDto> getAllProducts(int pageNo, int pageSize, String searchKeyword) {
        Pageable pageable = PageRequest.of(pageNo , pageSize);

        if(searchKeyword == null || searchKeyword.isEmpty()){
           Page<HealthProduct> healthProducts= healthProductRepository.findAll(pageable);

           if(healthProducts.isEmpty()){
               throw new ResponseStatusException(HttpStatus.NOT_FOUND , "No data found");
           }

           return healthProducts.map(it->
                   new HealthProductDto(it.getProductId() , it.getProductName() , it.getProductType())
                   );
        }else{
            Page<HealthProduct> healthProducts= healthProductRepository.findAllByKeyword(searchKeyword , pageable);

            if(healthProducts.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND , "No data found");
            }

            return healthProducts.map(it->
                    new HealthProductDto(it.getProductId() , it.getProductName() , it.getProductType())
            );
        }
    }

    @Override
    public HealthProductDto updateProduct(HealthProductDto dto, Long id) {
        return healthProductRepository.findById(id)
                .map(existingProduct->{

                    if(healthProductRepository.existsByProductName(dto.getProductName())){
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Product with this name already exist");
                    }
                    if(dto.getProductName() != null  && !dto.getProductName().trim().isEmpty()){
                         existingProduct.setProductName(dto.getProductName());
                    }

                    if(dto.getProductType() != null && !dto.getProductType().trim().isEmpty()){
                        existingProduct.setProductName(dto.getProductName());
                    }

                    HealthProduct updatedProduct = healthProductRepository.save(existingProduct);

                    return HealthProductDto.builder().id(updatedProduct.getProductId())
                            .productName(updatedProduct.getProductName())
                            .productType(updatedProduct.getProductType())
                            .build();
                })
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST , "Product with " + id + " id not found"));
    }


}
