package org.dongguk.mlac.event;

import lombok.Builder;
import org.dongguk.mlac.dto.type.EArea;
import org.dongguk.mlac.dto.type.EBlock;
import org.dongguk.mlac.dto.type.EOrganizer;

@Builder
public record UpdateUserStateEvent(
        String username,
        EArea area,
        EBlock updateType,
        EOrganizer organizer
) {
}
