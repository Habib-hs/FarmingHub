package com.project.annotations.entities;
import lombok.*;
import jakarta.persistence.*;
@Entity
@Table(name = "user_preferences")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferenceId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private String theme;

    private String language;

    @Column(nullable = false)
    private boolean receiveEmailNotifications = true;

    @Column(nullable = false)
    private boolean receiveSmsNotifications = false;
}