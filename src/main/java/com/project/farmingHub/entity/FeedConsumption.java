package com.project.farmingHub.Entity;

import lombok.*;
import jakarta.persistence.*;import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "feed_consumption")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedConsumptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flock_id", nullable = false)
    private Flock flock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_inventory_id", nullable = false)
    private FeedInventory feedInventory;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private BigDecimal quantity;
}
