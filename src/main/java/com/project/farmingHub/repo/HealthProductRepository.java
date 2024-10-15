package com.project.farmingHub.repo;

import com.project.farmingHub.entity.HealthProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthProductRepository extends JpaRepository<HealthProduct , Long> {
    HealthProduct findByProductName(String productName);

    boolean existsByProductName(String productName);

    Page<HealthProduct> findAllByKeyword(String searchKeyword, Pageable pageable);

}
