package com.project.farmingHub.model.Breed;

import com.project.farmingHub.model.HealthProductDto;

import java.util.List;

public class BreedGetDto {

    private Long breedId;

    private String breedName;

    private List<HealthProductDto> healthProductDtoList;


    public BreedGetDto(final Long breedId, final String breedName, final List<HealthProductDto> healthProductDtoList) {
        this.breedId = breedId;
        this.breedName = breedName;
        this.healthProductDtoList = healthProductDtoList;
    }


    public static BreedGetDto.BreedBuilder builder(){
        return new BreedGetDto.BreedBuilder();
    }

    public static class BreedBuilder {

        private Long breedId;

        private String breedName;

        private List<HealthProductDto> healthProductDtoList;

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


        public BreedBuilder healthProductDtoList(final List<HealthProductDto> healthProductDtoList){
            this.healthProductDtoList = healthProductDtoList;
            return this;
        }

        public BreedGetDto build(){
            return new BreedGetDto(this.breedId , this.breedName , this.healthProductDtoList);
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
