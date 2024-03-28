package org.dongguk.mlac.controller;

import lombok.RequiredArgsConstructor;
import org.dongguk.mlac.dto.request.AnalysisResultDto;
import org.dongguk.mlac.dto.common.ResponseDto;
import org.dongguk.mlac.dto.request.UpdateIPStateDto;
import org.dongguk.mlac.service.IPStateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ips")
@RequiredArgsConstructor
public class IPController {
    private final IPStateService ipStateService;

    @PostMapping("")
    public ResponseDto<?> createIPState(
            @RequestBody AnalysisResultDto analysisResultDto
    ){
        ipStateService.createIPState(analysisResultDto);
        return ResponseDto.ok(null);
    }

    @PatchMapping("/{ip}")
    public ResponseDto<?> updateIPState(
            @PathVariable("ip") String ip,
            @RequestBody UpdateIPStateDto updateIPStateDto
    ){
        ipStateService.updateIPState(ip, updateIPStateDto);
        return ResponseDto.ok(null);
    }
}
