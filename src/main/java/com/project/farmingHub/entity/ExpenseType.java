package com.project.farmingHub.Entity;
import lombok.*;
import jakarta.persistence.*;import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "expense_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseTypeId;

    @Column(nullable = false, unique = true)
    private String expenseTypeName;

    @OneToMany(mappedBy = "expenseType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Expense> expenses = new HashSet<>();
}
