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

        var sensorQueryExpression = new SensorQueryExpression(qSensor);

        return queryFactory
                .select(qSensor)
                .from(qSensor)
                .where(
                        sensorQueryExpression.inId(request.getId()),
                        sensorQueryExpression.inName(request.getName()),
                        sensorQueryExpression.inType(request.getType()),
                        sensorQueryExpression.likeId(request.getLikeId()),
                        sensorQueryExpression.likeName(request.getLikeName()),
                        sensorQueryExpression.likeType(request.getLikeType())
                )
                .fetch();
    }
}
