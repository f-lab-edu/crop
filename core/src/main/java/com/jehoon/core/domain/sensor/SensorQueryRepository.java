package com.jehoon.core.domain.sensor;

import com.jehoon.core.domain.sensor.dto.SensorSelectRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SensorQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Sensor> select(SensorSelectRequest request) {
        var qSensor = QSensor.sensor;

        return queryFactory
                .select(qSensor)
                .from(qSensor)
                .where(
                        qSensor.id.in(request.getId()),
                        qSensor.name.in(request.getName()),
                        qSensor.type.in(request.getType()),
                        qSensor.id.containsIgnoreCase(request.getLikeId()),
                        qSensor.name.containsIgnoreCase(request.getLikeName()),
                        qSensor.type.containsIgnoreCase(request.getLikeType())
                )
                .fetch();
    }
}
