package com.example.building_service.model;

import com.example.building_service.enums.BuildingStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table("buildings")
public class BuildingEntity {

    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    private String buildingName;

    @Column("status")
    private BuildingStatus buildingStatus;

    private int capacity, availableCapacity;

    public BuildingEntity(String buildingName, BuildingStatus buildingStatus, int capacity) {
        this.buildingName = buildingName;
        this.buildingStatus = buildingStatus;
        this.capacity = capacity;
        this.availableCapacity = capacity;
    }
}
