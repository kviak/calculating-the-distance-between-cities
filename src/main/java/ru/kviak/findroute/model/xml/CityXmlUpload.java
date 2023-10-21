package ru.kviak.findroute.model.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityXmlUpload {
    private String name;
    private double longitude;
    private double latitude;
}
