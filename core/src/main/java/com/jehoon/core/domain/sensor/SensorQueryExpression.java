package com.jehoon.core.domain.sensor;

import com.jehoon.core.utils.CustomCollectionUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
public class SensorQueryExpression {

    private final QSensor entity;

    public BooleanExpression inId(String... param) {
        return CustomCollectionUtil.isNullOrEmptyWithFirstValue(param) ? null : entity.id.in(param);
    }

    public BooleanExpression inName(String... param) {
        return CustomCollectionUtil.isNullOrEmptyWithFirstValue(param) ? null : entity.name.in(param);
    }

    public BooleanExpression inType(String... param) {
        return CustomCollectionUtil.isNullOrEmptyWithFirstValue(param) ? null : entity.type.in(param);
    }

    public BooleanExpression likeId(String param) {
        return StringUtils.isEmpty(param) ? null : entity.id.containsIgnoreCase(param);
    }

    public BooleanExpression likeName(String param) {
        return StringUtils.isEmpty(param) ? null : entity.name.containsIgnoreCase(param);
    }

    public BooleanExpression likeType(String param) {
        return StringUtils.isEmpty(param) ? null : entity.type.containsIgnoreCase(param);
    }

}
