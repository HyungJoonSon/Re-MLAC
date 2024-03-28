package org.dongguk.mlac.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.mlac.domain.User;
import org.dongguk.mlac.dto.request.UpdateUserStateRequestDto;
import org.dongguk.mlac.dto.type.EArea;
import org.dongguk.mlac.dto.type.EBlock;
import org.dongguk.mlac.dto.type.EOrganizer;
import org.dongguk.mlac.event.UpdateUserStateEvent;
import org.dongguk.mlac.exception.CommonException;
import org.dongguk.mlac.exception.ErrorCode;
import org.dongguk.mlac.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void updateUserState(UpdateUserStateRequestDto updateUserStateRequestDto){
        // 주최자를 확인하고 주최자가 없으면 return
        EOrganizer organizer = EOrganizer.fromEAttack(updateUserStateRequestDto.attackType());
        String username = (String) updateUserStateRequestDto.body().get("username");
        Boolean isBlocked = (Boolean) updateUserStateRequestDto.body().get("is_blocked");

        if (organizer == null || username == null || isBlocked == null) {
            return;
        }

        // 영역을 찾고, ObservingSystem이면 전체 유저에서 검색하고, 아니면 해당 영역에서 검색
        EArea area = EArea.fromEOrganizer(organizer);

        User user;

        if (area == null){
            user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
        } else {
            user = userRepository.findByUsernameAndArea(username, area)
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
        }

        // Update할 필요가 없다면 return하고 필요하다면 update
        if (user.getIsBlocked() == isBlocked){
            return;
        }

        user.updateBlock(isBlocked);

        applicationEventPublisher.publishEvent(UpdateUserStateEvent.builder()
                .username(user.getUsername())
                .area(user.getArea())
                .updateType(user.getIsBlocked() ? EBlock.BLOCK : EBlock.UNBLOCK)
                .organizer(organizer).build()
        );
    }
}
