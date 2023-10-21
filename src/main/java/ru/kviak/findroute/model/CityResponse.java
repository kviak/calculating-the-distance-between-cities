package ru.kviak.findroute.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.kviak.findroute.api.view.View;

@Data
@AllArgsConstructor
public class CityResponse { // TODO: JsonView,
    @JsonView({View.DistanceView.class, View.CityView.class})
    private long id;
    @JsonView({View.DistanceView.class, View.CityView.class})
    private String name;
    @JsonView(View.DistanceView.class)
    private double longitude;
    @JsonView(View.DistanceView.class)
    private double latitude;
}
