package org.dongguk.mlac.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dongguk.mlac.dto.type.EScript;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "dummy_scripts")
public class DummyScript {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content", nullable = false, updatable = false)
    private String content;

    @Column(name = "type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private EScript type;

    @OneToMany(mappedBy = "dummyScript", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DummyScriptRegex> dummyScriptRegexes = new ArrayList<>();

    @Builder
    public DummyScript(String content, EScript type) {
        this.content = content;
        this.type = type;
    }
}
