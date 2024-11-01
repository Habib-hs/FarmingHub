package com.project.farmingHub.domain;
import lombok.*;
import jakarta.persistence.*;import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "health_event_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthEventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventTypeId;

    @Column(nullable = false, unique = true)
    private String eventTypeName;

    @OneToMany(mappedBy = "eventType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HealthEvent> healthEvents = new HashSet<>();
}