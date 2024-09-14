package com.example.building_service.model;

import com.example.building_service.enums.BuildingNameType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("buildings")
@Getter
@Setter
@ToString
public class BuildingEntity {

    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    private String buildingName;

    private int capacity;

    public BuildingEntity(String buildingName, int capacity) {
        this.buildingName = buildingName;
        this.capacity = capacity;
    }
}
