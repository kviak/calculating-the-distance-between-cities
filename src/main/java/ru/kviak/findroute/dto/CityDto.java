package ru.kviak.findroute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityDto {
    private long id;
    private String name;
    private double longitude;
    private double latitude;
}
