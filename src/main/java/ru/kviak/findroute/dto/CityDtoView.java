package ru.kviak.findroute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDtoView {
    private String name;
    private double longitude;
    private double latitude;
}
