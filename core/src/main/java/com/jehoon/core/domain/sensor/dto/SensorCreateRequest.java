package com.jehoon.core.domain.sensor.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class SensorCreateRequest {
    
    @NotEmpty
    private String id;
    private String name;
    private String type;
}
