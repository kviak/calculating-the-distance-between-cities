package ru.kviak.findroute.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitiesAndDistancesDto {
    @JacksonXmlElementWrapper(localName = "cities")
    private List<CityDtoView> cities;

    @JacksonXmlElementWrapper(localName = "distances")
    private List<DistanceDto> distances;
}
