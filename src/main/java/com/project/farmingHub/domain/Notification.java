package com.project.farmingHub.domain;
import com.project.annotations.entities.User;
import com.project.farmingHub.enums.NotificationType;
import lombok.*;
import jakarta.persistence.*;import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flock_id", nullable = false)
    private Flock flock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType;

    @Column(nullable = false)
    private LocalDateTime sentDate;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean isRead = false;

}