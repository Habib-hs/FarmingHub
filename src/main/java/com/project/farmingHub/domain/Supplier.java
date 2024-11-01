package com.project.farmingHub.domain;
import lombok.*;
import jakarta.persistence.*;import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "supplier")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    @Column(nullable = false)
    private String supplierName;

    @Column(nullable = false)
    private String contactInfo;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FeedInventory> feedInventories = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "supplier_feed_type",
            joinColumns = @JoinColumn(name = "supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "feed_type_id")
    )
    private Set<FeedType> suppliedFeedTypes = new HashSet<>();
}