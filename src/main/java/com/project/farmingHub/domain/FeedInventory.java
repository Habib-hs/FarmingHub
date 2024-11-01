package com.project.farmingHub.domain;

import lombok.*;
import jakarta.persistence.*;import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "feed_inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedInventoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_type_id", nullable = false)
    private FeedType feedType;

    @Column(nullable = false)
    private BigDecimal quantity;

    @Column(nullable = false)
    private BigDecimal cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @OneToMany(mappedBy = "feedInventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FeedConsumption> feedConsumptions = new HashSet<>();
}