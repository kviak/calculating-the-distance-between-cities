package ru.kviak.findroute.model.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Distance {
    private String fromCity;
    private String toCity;
    private Double distance;
}
