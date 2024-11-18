package com.jehoon.core.domain.sensor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorSelectRequest {

    private String[] id;
    private String[] name;
    private String[] type;

    private String likeId;
    private String likeName;
    private String likeType;
}
