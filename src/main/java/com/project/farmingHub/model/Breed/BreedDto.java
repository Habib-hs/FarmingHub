package com.project.farmingHub.model.Breed;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreedDto {

    private Long breedId;

    @NotBlank(message = "Breed name cannot be blank")
    private String breedName;

    @NotEmpty(message = "Breed must have at least one health product")
    private Set<String> productSet = new HashSet<>();
}
