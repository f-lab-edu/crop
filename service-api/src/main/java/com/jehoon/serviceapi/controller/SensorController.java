package com.jehoon.serviceapi.controller;

import com.jehoon.core.domain.sensor.SensorService;
import com.jehoon.core.domain.sensor.dto.SensorCreateRequest;
import com.jehoon.core.domain.sensor.dto.SensorResponse;
import com.jehoon.core.domain.sensor.dto.SensorSelectRequest;
import com.jehoon.core.domain.sensor.dto.SensorUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sensor")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @GetMapping
    public List<SensorResponse> select(@ParameterObject SensorSelectRequest request) {
        return sensorService.select(request);
    }

    @PostMapping
    public SensorResponse create(@Validated @RequestBody SensorCreateRequest request) {
        return sensorService.create(request);
    }

    @PatchMapping
    public SensorResponse update(@Validated @RequestBody SensorUpdateRequest request) {
        return sensorService.update(request);
    }

    @DeleteMapping
    public List<SensorResponse> delete(@RequestParam("id") String[] request) {
        return sensorService.delete(request);
    }
}
