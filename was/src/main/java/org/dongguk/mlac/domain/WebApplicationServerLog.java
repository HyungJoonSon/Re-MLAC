package org.dongguk.mlac.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dongguk.mlac.dto.type.EArea;
import org.dongguk.mlac.dto.type.EBlock;
import org.dongguk.mlac.dto.type.EOrganizer;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "web_application_server_logs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebApplicationServerLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, updatable = false)
    private String username;

    @Column(name = "update_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private EBlock updateType;

    @Column(name = "organizer", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private EOrganizer organizer;

    @Column(name = "area", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private EArea area;

    @Column(name = "updated_at", nullable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Builder
    public WebApplicationServerLog(
            String username,
            EBlock updateType,
            EOrganizer organizer,
            EArea area
    ) {
        this.username = username;
        this.updateType = updateType;
        this.organizer = organizer;
        this.area = area;
        this.updatedAt = LocalDateTime.now();
    }
}
