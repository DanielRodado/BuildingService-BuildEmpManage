package com.example.building_service.enums;

import lombok.Getter;

@Getter
public enum BuildingNameType {

    B1A("1A"), B2A("2A"), B3A("3A"), B4A("4A"), B5A("5A"), B6A("6A"),
    B1B("1B"), B2B("2B"), B3B("3B"), B4B("4B"), B5B("5B"), B6B("6B"),
    B1C("1C"), B2C("2C"), B3C("3C"), B4C("4C"), B5C("5C"), B6C("6C"),
    B1D("1D"), B2D("2D"), B3D("3D"), B4D("4D"), B5D("5D"), B6D("6D"),
    B1E("1E"), B2E("2E"), B3E("3E"), B4E("4E"), B5E("5E"), B6E("6E"),
    B1F("1F"), B2F("2F"), B3F("3F"), B4F("4F"), B5F("5F"), B6F("6F");

    private final String value;

    BuildingNameType(String value) {
        this.value = value;
    }

}
