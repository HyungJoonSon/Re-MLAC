package org.dongguk.mlac.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dongguk.mlac.dto.type.EBlock;

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

    @Column(name = "update_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private EBlock updateType;

    @Column(name = "update_at", nullable = false, updatable = false)
    private LocalDateTime updateAt;

    @Builder
    public FirewallLog(
            String ip,
            EBlock updateType
    ) {
        this.ip = ip;
        this.updateType = updateType;
        this.updateAt = LocalDateTime.now();
    }
}
