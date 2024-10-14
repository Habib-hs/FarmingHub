package com.project.farmingHub.entity;
import com.project.farmingHub.validation.ValidatorProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.persistence.*;import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "health_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name" , nullable = false , unique = true)
    @NotBlank(message = "product name can not be blank")
    private String productName;

    @ValidatorProductType
    @Column(name = "product_type")
    private String productType;

    @ManyToMany(mappedBy = "healthProducts")
    private Set<Flock> flocks = new HashSet<>();

    @ManyToMany(mappedBy = "recommendedProducts")
    private Set<Breed> recommendedForBreeds = new HashSet<>();



}