package com.project.farmingHub.domain;

import lombok.*;
import jakarta.persistence.*;import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "egg_production")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EggProduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flock_id", nullable = false)
    private Flock flock;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal pricePerEgg;
}