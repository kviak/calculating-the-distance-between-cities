package ru.kviak.findroute.service;

import ru.kviak.findroute.model.CalculationType;
import ru.kviak.findroute.model.CityDistanceResponse;

import java.util.List;

public interface DistanceService {

    List<CityDistanceResponse> calculate(List<Long> fromCityIds, List<Long> toCityIds);

    CalculationType getCalculationType();

}
