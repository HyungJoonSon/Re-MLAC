package org.dongguk.mlac.controller;

import lombok.RequiredArgsConstructor;
import org.dongguk.mlac.dto.common.ResponseDto;
import org.dongguk.mlac.dto.request.AnalysisResultDto;
import org.dongguk.mlac.service.PipelineService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pipelines")
public class PipelineController {
    private final PipelineService pipelineService;

    @PostMapping()
    public ResponseDto<?> createPipeline(
            @RequestBody AnalysisResultDto analysisResultDto
    ){
        pipelineService.savePipeline(analysisResultDto);
        return ResponseDto.ok(null);
    }
}
