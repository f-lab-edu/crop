package com.jehoon.core.domain.sensor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Table(name = "sensors")
@Entity
@DynamicUpdate
@DynamicInsert
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Sensor {

    @Id
    @Column(length = 30)
    private String id;
    private String name;
    private String type;

    public Sensor setName(String name) {
        this.name = name;
        return this;
    }

    public Sensor setType(String type) {
        this.type = type;
        return this;
    }
}
