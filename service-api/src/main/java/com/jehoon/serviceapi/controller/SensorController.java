package com.jehoon.serviceapi.controller;

import com.jehoon.core.common.CommonResponse;
import com.jehoon.core.common.ICommonResposne;
import com.jehoon.core.domain.sensor.SensorService;
import com.jehoon.core.domain.sensor.dto.SensorCreateRequest;
import com.jehoon.core.domain.sensor.dto.SensorSelectRequest;
import com.jehoon.core.domain.sensor.dto.SensorUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sensor")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @GetMapping
    public ICommonResposne select(@ParameterObject SensorSelectRequest request) {
        return CommonResponse.of(sensorService.select(request));
    }

    @PostMapping
    public ICommonResposne create(@Validated @RequestBody SensorCreateRequest request) {
        return CommonResponse.of(sensorService.create(request));
    }

    @PatchMapping
    public ICommonResposne update(@Validated @RequestBody SensorUpdateRequest request) {
        return CommonResponse.of(sensorService.update(request));
    }

    @DeleteMapping
    public ICommonResposne delete(@RequestParam("id") String[] request) {
        return CommonResponse.of(sensorService.delete(request));
    }
}
