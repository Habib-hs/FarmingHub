package com.project.farmingHub.domain;
import lombok.*;
import jakarta.persistence.*;import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "health_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long healthEventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flock_id", nullable = false)
    private Flock flock;

    @Column(nullable = false)
    private LocalDate eventDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_type_id", nullable = false)
    private HealthEventType eventType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private HealthProduct product;

    @Column(nullable = false)
    private String dosage;

    @Column(nullable = false)
    private String administeredBy;

    @Column(nullable = false)
    private BigDecimal cost;
}