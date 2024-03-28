package org.dongguk.mlac.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.mlac.domain.IPState;
import org.dongguk.mlac.dto.request.AnalysisResultDto;
import org.dongguk.mlac.dto.request.UpdateIPStateDto;
import org.dongguk.mlac.dto.type.EBlock;
import org.dongguk.mlac.event.CreateIPStateEvent;
import org.dongguk.mlac.event.UpdateIPStateEvent;
import org.dongguk.mlac.exception.CommonException;
import org.dongguk.mlac.exception.ErrorCode;
import org.dongguk.mlac.repository.IPStateRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IPStateService {
    private final IPStateRepository ipStateRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public void createIPState(AnalysisResultDto analysisResultDto) {
        // 공격이 아니라면 무시
        if ("BENIGN".equals(analysisResultDto.attackType())) {
            return ;
        }

        // IPState가 이미 존재한다면 예외 처리
        ipStateRepository.findByIp(analysisResultDto.ip())
                .orElseThrow(() -> new CommonException(ErrorCode.DUPLICATE_RESOURCE));

        // IPState가 차단되어 있지 않다면 차단
        IPState ipState = ipStateRepository.save(IPState.builder()
                .ip(analysisResultDto.ip())
                .isBlocked(true).build()
        );

        // Event 전파
        applicationEventPublisher.publishEvent(CreateIPStateEvent.builder()
                .ip(ipState.getIp()).build()
        );
    }

    @Transactional
    public void updateIPState(String ip, UpdateIPStateDto updateIPStateDto) {
        // IPState가 없다면 예외 처리
        IPState ipState = ipStateRepository.findByIp(ip)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 만약 차단 상태가 같다면 무시
        if (ipState.getIsBlocked() == updateIPStateDto.isBlocked()) {
            throw new CommonException(ErrorCode.ALREADY_UPDATED);
        }

        // IPState 업데이트
        ipState.updateBlocked(updateIPStateDto.isBlocked());

        // Event 전파
        applicationEventPublisher.publishEvent(UpdateIPStateEvent.builder()
                .ip(ipState.getIp())
                .updateType(ipState.getIsBlocked() ? EBlock.BLOCK : EBlock.UNBLOCK).build()
        );
    }
}
