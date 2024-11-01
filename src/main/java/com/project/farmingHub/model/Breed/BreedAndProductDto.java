package com.project.farmingHub.model.Breed;

import com.project.farmingHub.model.HealthProduct.HealthProductDto;
import lombok.Data;


import java.util.Set;

@Data
public class BreedAndProductDto {

    private Long breedId;

    private String breedName;

    private Set<HealthProductDto> healthProductDtoList;


    public BreedAndProductDto() {

    }

    public BreedAndProductDto(final Long breedId, final String breedName, final Set<HealthProductDto> healthProductDtoList) {
        this.breedId = breedId;
        this.breedName = breedName;
        this.healthProductDtoList = healthProductDtoList;
    }


    public static BreedAndProductDto.BreedBuilder builder(){
        return new BreedAndProductDto.BreedBuilder();
    }

    public static class BreedBuilder {

        private Long breedId;

        private String breedName;

        private Set<HealthProductDto> healthProductDtoList;

        BreedBuilder(){

        }


        public BreedBuilder breedId(final Long id){
           this.breedId = id;
           return this;
        }


        public BreedBuilder breedName(final String breedName){
            this.breedName = breedName;
            return this;
        }


        public BreedBuilder healthProductDtoList(final Set<HealthProductDto> healthProductDtoList){
            this.healthProductDtoList = healthProductDtoList;
            return this;
        }

        public BreedAndProductDto build(){
            return new BreedAndProductDto(this.breedId , this.breedName , this.healthProductDtoList);
        }


        @Override
        public String toString() {
            return "BreedBuilder{" +
                    "breedId=" + breedId +
                    ", breedName='" + breedName + '\'' +
                    ", healthProductDtoList=" + healthProductDtoList +
                    '}';
        }
    }
}
