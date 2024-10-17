package com.project.farmingHub.repo;

import com.project.farmingHub.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<Breed , Long> {
    boolean existsByBreedName(String breed);

}
