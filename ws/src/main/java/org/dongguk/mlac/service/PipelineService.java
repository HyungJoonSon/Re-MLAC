package org.dongguk.mlac.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.mlac.domain.Pipeline;
import org.dongguk.mlac.domain.Regex;
import org.dongguk.mlac.dto.request.AnalysisResultDto;
import org.dongguk.mlac.dto.type.EScript;
import org.dongguk.mlac.event.CreatePipelineEvent;
import org.dongguk.mlac.repository.PipelineRepository;
import org.dongguk.mlac.repository.RegexRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PipelineService {
    private final PipelineRepository pipelineRepository;
    private final RegexRepository regexRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public void savePipeline(AnalysisResultDto analysisResultDto) {
        EScript type = EScript.find(analysisResultDto.attackType().toString());
        String script = analysisResultDto.body().get("script");

        // Pipeline을 만들지 않는 공격이라면 저장하지 않음
        if (type == null || script == null) {
            return;
        }

        // 해당 script에 대한 regex를 찾아서 pipeline을 만듦
        List<Regex> regexes = regexRepository.findAllByDummyScriptContent(script);
        List<Regex> addedRegexes = new ArrayList<>();

        while (!regexes.isEmpty()) {
            Regex regex = regexes.get(0);
            regexes.remove(0);

            try {
                pipelineRepository.save(Pipeline.builder()
                        .regex(regex.getContent()).build()
                );

                addedRegexes.add(regex);
            } catch (Exception ignored) {
            }
        }

        // 추가된 pipeline에 대해 이벤트를 발생시킴
        for (Regex regex : addedRegexes) {
            applicationEventPublisher.publishEvent(CreatePipelineEvent.builder()
                    .regex(regex.getContent()).build()
            );
        }
    }
}
