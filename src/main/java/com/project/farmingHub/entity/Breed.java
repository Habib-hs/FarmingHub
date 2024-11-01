package com.project.farmingHub.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "breed")
@Getter
@Setter
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

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(
            name = "breed_health_product",
            joinColumns = @JoinColumn(name = "breed_id"),
            inverseJoinColumns = @JoinColumn(name = "health_product_id")
    )
    @JsonManagedReference
    private Set<HealthProduct> recommendedProducts = new HashSet<>();
}