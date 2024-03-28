package org.dongguk.mlac.event;

import lombok.Builder;
import org.dongguk.mlac.dto.type.EBlock;

@Builder
public record CreateIPStateEvent(
        String ip
) {
}
