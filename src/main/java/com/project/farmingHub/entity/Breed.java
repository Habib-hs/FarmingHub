package com.project.farmingHub.Entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "breed")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long breedId;

    @Column(nullable = false, unique = true)
    private String breedName;

    @OneToMany(mappedBy = "breed", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Flock> flocks = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "breed_health_product",
            joinColumns = @JoinColumn(name = "breed_id"),
            inverseJoinColumns = @JoinColumn(name = "health_product_id")
    )
    private Set<HealthProduct> recommendedProducts = new HashSet<>();
}