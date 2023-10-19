package ru.kviak.findroute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.kviak.findroute.model.City;

@Data
@AllArgsConstructor
public class DistanceDto {
    private String fromCity;
    private String toCity;
    private Double distance;
}
