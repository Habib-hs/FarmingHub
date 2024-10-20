package com.project.farmingHub.repo;

import aj.org.objectweb.asm.commons.Remapper;
import com.project.farmingHub.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BreedRepository extends JpaRepository<Breed , Long> {
    boolean existsByBreedName(String breed);

    @Query("SELECT b FROM Breed b JOIN FETCH b.recommendedProducts rp WHERE b.breedId = :id")
    Optional<Breed> findByIdWithProducts(@Param("id") Long id);

}
