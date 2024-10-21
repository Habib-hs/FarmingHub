package com.project.farmingHub.repo;

import com.project.farmingHub.domain.HealthProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HealthProductRepository extends JpaRepository<HealthProduct , Long> {
    Optional<HealthProduct> findByProductName(String productName);

    boolean existsByProductName(String productName);

    @Query("SELECT hp FROM HealthProduct hp WHERE LOWER(hp.productName) " +
            "LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(hp.productType) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "ORDER BY hp.productId desc")
    Page<HealthProduct> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
