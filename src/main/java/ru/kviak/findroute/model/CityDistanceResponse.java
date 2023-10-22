package ru.kviak.findroute.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kviak.findroute.api.view.View;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDistanceResponse {
    @JsonView(View.DistanceView.class)
    private CityResponse fromCity;
    @JsonView(View.DistanceView.class)
    private CityResponse toCity;
    @JsonView(View.DistanceView.class)
    private BigDecimal distance;
}
