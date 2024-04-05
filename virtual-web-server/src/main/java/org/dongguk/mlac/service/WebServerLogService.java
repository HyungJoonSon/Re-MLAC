package org.dongguk.mlac.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.mlac.domain.WebServerLog;
import org.dongguk.mlac.dto.type.ELogStatus;
import org.dongguk.mlac.event.CreatePipelineEvent;
import org.dongguk.mlac.repository.WebServerLogRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebServerLogService {
    private final WebServerLogRepository webServerLogRepository;

    /**
     * @description 이벤트를 받아 WebServerLog 생성
     * @param event
     */
    @Async @EventListener
    public void saveWebServerLog(CreatePipelineEvent event) {
        webServerLogRepository.save(WebServerLog.builder()
                .regex(event.regex())
                .status(ELogStatus.CREATE)
                .organizer(event.organizer())
                .build()
        );
    }
}
