package com.project.farmingHub.repo;

import com.project.farmingHub.domain.Breed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BreedRepository extends JpaRepository<Breed , Long> {
    boolean existsByBreedName(String breed);


    @Query("SELECT b FROM Breed b WHERE LOWER(b.breedName) " +
            "LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.breedName) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "ORDER BY b.breedId desc")
    Page<Breed> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
