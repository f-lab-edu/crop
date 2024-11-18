package com.jehoon.core.domain.sensor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jehoon.core.domain.sensor.Sensor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SensorResponse {

    private String id;
    private String name;
    private String type;

    public static SensorResponse fromEntity(Sensor entity) {
        if (entity == null) {
            return null;
        }

        return SensorResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .build();
    }
}
