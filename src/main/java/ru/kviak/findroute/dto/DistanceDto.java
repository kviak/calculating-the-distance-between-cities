package ru.kviak.findroute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistanceDto {
    private String fromCity;
    private String toCity;
    private Double distance;
}
