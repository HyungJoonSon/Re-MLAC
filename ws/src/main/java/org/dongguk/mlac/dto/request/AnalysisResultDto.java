package org.dongguk.mlac.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.dongguk.mlac.dto.type.EAttack;

import java.util.Map;

@Builder
public record AnalysisResultDto (
        @JsonProperty("ip")
        String ip,

        @JsonProperty("port")
        String port,

        @JsonProperty("body")
        Map<String, String> body,

        @JsonProperty("timestamp")
        String timestamp,

        @JsonProperty("attack_type")
        EAttack attackType
) {
}
