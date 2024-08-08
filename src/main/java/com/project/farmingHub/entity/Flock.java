package com.project.farmingHub.Entity;
import lombok.*;
import jakarta.persistence.*;import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flock")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flockId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id", nullable = false)
    private Breed breed;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(nullable = false)
    private Integer initialBirdCount;

    private Integer currentBirdCount;

    private BigDecimal totalCost;

    private BigDecimal totalRevenue;

    @OneToMany(mappedBy = "flock", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EggProduction> eggProductions = new HashSet<>();

    @OneToMany(mappedBy = "flock", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Mortality> mortalities = new HashSet<>();

    @OneToMany(mappedBy = "flock", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HealthEvent> healthEvents = new HashSet<>();

    @OneToMany(mappedBy = "flock", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FeedConsumption> feedConsumptions = new HashSet<>();

    @ManyToMany(mappedBy = "managedFlocks")
    private Set<User> managers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "flock_health_product",
            joinColumns = @JoinColumn(name = "flock_id"),
            inverseJoinColumns = @JoinColumn(name = "health_product_id")
    )
    private Set<HealthProduct> healthProducts = new HashSet<>();
}
