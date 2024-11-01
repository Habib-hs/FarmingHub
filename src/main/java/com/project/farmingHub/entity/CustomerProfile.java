package com.project.farmingHub.entity;

import lombok.*;
import jakarta.persistence.*;import java.time.LocalDate;

@Entity
@Table(name = "customer_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", unique = true)
    private Customer customer;

    private LocalDate birthDate;

    private String address;

    @Column(length = 1000)
    private String notes;
}