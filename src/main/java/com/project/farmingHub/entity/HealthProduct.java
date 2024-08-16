package com.project.farmingHub.entity;
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
    private Long productId;

    @Column(nullable = false, unique = true)
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType;

    @ManyToMany(mappedBy = "healthProducts")
    private Set<Flock> flocks = new HashSet<>();

    @ManyToMany(mappedBy = "recommendedProducts")
    private Set<Breed> recommendedForBreeds = new HashSet<>();

    public enum ProductType {
        VACCINE, MEDICATION
    }
}