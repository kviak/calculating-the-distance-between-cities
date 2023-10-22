package ru.kviak.findroute.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitiesAndDistancesWrapper {
    @JacksonXmlElementWrapper(localName = "cities")
    private List<CityXmlUpload> cities;

    @JacksonXmlElementWrapper(localName = "distances")
    private List<Distance> distances;
}
