package org.dongguk.mlac.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.dongguk.mlac.dto.type.EAttack;

public record UpdateUserStateRequestDto(
        @JsonProperty("user_name")
        String username,

        @JsonProperty("attack_type")
        EAttack attackType,

        @JsonProperty("is_blocked")
        Boolean isBlocked
) {
}
