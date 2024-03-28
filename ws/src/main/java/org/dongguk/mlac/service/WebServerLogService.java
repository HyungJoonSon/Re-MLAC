package org.dongguk.mlac.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.mlac.domain.Pipeline;
import org.dongguk.mlac.domain.WebServerLog;
import org.dongguk.mlac.dto.request.AnalysisResultDto;
import org.dongguk.mlac.dto.type.EAttack;
import org.dongguk.mlac.event.CreatePipelineEvent;
import org.dongguk.mlac.repository.PipelineRepository;
import org.dongguk.mlac.repository.WebServerLogRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class WebServerLogService {
    private final WebServerLogRepository webServerLogRepository;

    @Async @EventListener
    public void createWebServerLog(CreatePipelineEvent event) {
        webServerLogRepository.save(WebServerLog.builder()
                .regex(event.regex()).build()
        );
    }
}
