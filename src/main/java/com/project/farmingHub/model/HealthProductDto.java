package com.project.farmingHub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthProductDto implements Serializable {

    private static final long serialVersionUID = -4439114469417994311L;


    private Long id;
    private String productName;
    private String productType;
}
