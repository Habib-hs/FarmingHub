package com.project.farmingHub.domain;

import lombok.*;
import jakarta.persistence.*;import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cause_of_death")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CauseOfDeath {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long causeId;

    @Column(nullable = false, unique = true)
    private String causeName;

    @OneToMany(mappedBy = "cause", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Mortality> mortalities = new HashSet<>();
}