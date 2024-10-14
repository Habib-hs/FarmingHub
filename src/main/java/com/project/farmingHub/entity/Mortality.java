package com.project.farmingHub.entity;
import lombok.*;
import jakarta.persistence.*;import java.time.LocalDate;

@Entity
@Table(name = "mortality")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mortality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mortalityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flock_id", nullable = false)
    private Flock flock;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cause_id", nullable = false)
    private CauseOfDeath cause;

    @Column(nullable = false)
    private Integer numberOfDeaths;
}
