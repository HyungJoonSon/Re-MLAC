package org.dongguk.mlac.event;

import lombok.Builder;

@Builder
public record CreatePipelineEvent(
        String regex
) {
}
