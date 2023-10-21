package ru.kviak.findroute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kviak.findroute.model.CalculationType;
import ru.kviak.findroute.model.CityDistanceResponse;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AllDistanceService implements DistanceService{
    private final CrowflightDistanceService crowflightDistanceService;
    private final MatrixDistanceService matrixDistanceService;

    @Override
    public List<CityDistanceResponse> calculate(List<Long> fromCityIds, List<Long> toCityIds) {
        List<CityDistanceResponse> response = crowflightDistanceService.calculate(fromCityIds, toCityIds);
        response.addAll(matrixDistanceService.calculate(fromCityIds, toCityIds));
        return response;
    }

    @Override
    public CalculationType getCalculationType() {
        return CalculationType.ALL;
    }
}
