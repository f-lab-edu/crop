package com.jehoon.core.domain.sensor;

import com.jehoon.core.domain.sensor.dto.SensorCreateRequest;
import com.jehoon.core.domain.sensor.dto.SensorResponse;
import com.jehoon.core.domain.sensor.dto.SensorSelectRequest;
import com.jehoon.core.domain.sensor.dto.SensorUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final SensorQueryRepository sensorQueryRepository;

    @Transactional(readOnly = true)
    public List<SensorResponse> select(SensorSelectRequest request) {
        return sensorQueryRepository.select(request)
                .stream()
                .map(SensorResponse::fromEntity)
                .toList();
    }

    public SensorResponse create(SensorCreateRequest request) {
        var createTarget = Sensor.builder()
                .id(request.getId())
                .name(request.getName())
                .type(request.getType())
                .build();

        // ID 중복 검사
        sensorRepository
                .findById(createTarget.getId())
                .ifPresent(sensor -> {
                    throw new RuntimeException("이미 존재하는 ID 입니다");
                });

        sensorRepository.save(createTarget);

        return SensorResponse.fromEntity(createTarget);
    }

    public SensorResponse update(SensorUpdateRequest request) {
        var updateTarget = sensorRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("ID 값을 가진 센서가 존재하지 않습니다"));

        updateTarget.setName(request.getName());
        updateTarget.setName(request.getType());

        return SensorResponse.fromEntity(updateTarget);
    }

    public SensorResponse delete(String id) {
        var deleteTarget = sensorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID 값을 가진 센서가 존재하지 않습니다"));
        sensorRepository.delete(deleteTarget);
        return SensorResponse.fromEntity(deleteTarget);
    }
}
