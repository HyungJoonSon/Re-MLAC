package org.dongguk.mlac.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dongguk.mlac.dto.type.ELogStatus;
import org.dongguk.mlac.dto.type.EOrganizer;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "firewall_logs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FirewallLog {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ip", nullable = false, updatable = false)
    private String ip;

    @Column(name = "status", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private ELogStatus status;

    @Column(name = "organizer", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private EOrganizer organizer;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public FirewallLog(
            String ip,
            ELogStatus status,
            EOrganizer organizer
    ) {
        this.ip = ip;
        this.status = status;
        this.organizer = organizer;
        this.createdAt = LocalDateTime.now();
    }
}
