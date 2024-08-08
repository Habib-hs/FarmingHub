package com.project.farmingHub.Entity;
import lombok.*;
import jakarta.persistence.*;import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "feed_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedTypeId;

    @Column(nullable = false, unique = true)
    private String feedTypeName;

    @OneToMany(mappedBy = "feedType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FeedInventory> feedInventories = new HashSet<>();

    @ManyToMany(mappedBy = "suppliedFeedTypes")
    private Set<Supplier> suppliers = new HashSet<>();

    @ManyToMany(mappedBy = "preferredFeedTypes")
    private Set<Customer> interestedCustomers = new HashSet<>();
}