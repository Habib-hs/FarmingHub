package com.project.farmingHub.model.Breed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BreedFetchDto {

    private Long breedId;

    private String breedName;
}
