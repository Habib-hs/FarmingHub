package com.project.farmingHub.domain;

import lombok.*;
import jakarta.persistence.*;import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contactInfo;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sale> sales = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "customer_feed_preference",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "feed_type_id")
    )
    private Set<FeedType> preferredFeedTypes = new HashSet<>();

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
    private CustomerProfile profile;
}