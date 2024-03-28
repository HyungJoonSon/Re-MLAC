package org.dongguk.mlac.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record UpdateIPStateDto(
        @JsonProperty("isBlocked")
        Boolean isBlocked
) {
}
