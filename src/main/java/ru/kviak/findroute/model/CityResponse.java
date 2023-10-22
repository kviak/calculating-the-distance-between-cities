package ru.kviak.findroute.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kviak.findroute.api.view.View;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityResponse {
    @JsonView({View.DistanceView.class, View.CityView.class})
    private long id;
    @JsonView({View.DistanceView.class, View.CityView.class})
    private String name;

    private double longitude;

    private double latitude;
}
