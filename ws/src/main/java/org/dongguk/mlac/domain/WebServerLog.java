package org.dongguk.mlac.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "web_server_logs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebServerLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "regex", nullable = false, updatable = false)
    private String regex;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public WebServerLog(String regex) {
        this.regex = regex;
        this.createdAt = LocalDateTime.now();
    }
}