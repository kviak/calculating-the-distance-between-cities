package ru.kviak.findroute.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kviak.findroute.model.CalculationType;
import ru.kviak.findroute.model.CityDistanceResponse;
import ru.kviak.findroute.api.view.View;
import ru.kviak.findroute.service.DistanceServiceProvider;

import java.util.List;

@RestController
@RequestMapping("/api/v1/distance-calculator")
@RequiredArgsConstructor
public class DistanceController {
    private final DistanceServiceProvider distanceServiceProvider;

    @JsonView(View.DistanceView.class)
    @PostMapping
    public List<CityDistanceResponse> calculateDistance(
            @RequestParam(name = "from-city") List<Long> fromCityId,
            @RequestParam(name = "to-city") List<Long> toCityId,
            @RequestParam(name = "calculation-type", defaultValue = "Crowflight") CalculationType calculationType
    ) {
        return distanceServiceProvider.getDistanceService(calculationType).calculate(fromCityId, toCityId);
    }
}
