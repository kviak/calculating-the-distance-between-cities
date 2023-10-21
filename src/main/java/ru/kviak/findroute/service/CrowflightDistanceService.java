package ru.kviak.findroute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kviak.findroute.model.CalculationType;
import ru.kviak.findroute.model.CityDistanceResponse;
import ru.kviak.findroute.mapper.CityMapper;
import ru.kviak.findroute.persistence.entity.CityEntity;
import ru.kviak.findroute.persistence.repository.CityRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CrowflightDistanceService implements DistanceService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CityDistanceResponse> calculate(final List<Long> fromCityIds, final List<Long> toCityIds) {
        List<CityEntity> fromCities = cityRepository.findAllByIdIn(fromCityIds);
        List<CityEntity> toCities = cityRepository.findAllByIdIn(toCityIds);
        List<CityDistanceResponse> response = new ArrayList<>(fromCities.size() * toCities.size());

        for (CityEntity fromCityEntity : fromCities) {
            for (CityEntity toCityEntity : toCities) {
                response.add(
                        new CityDistanceResponse(
                                cityMapper.mapTo(fromCityEntity),
                                cityMapper.mapTo(toCityEntity),
                                BigDecimal.valueOf(
                                        haversine(
                                                fromCityEntity.getLatitude(),
                                                fromCityEntity.getLongitude(),
                                                toCityEntity.getLatitude(),
                                                toCityEntity.getLongitude()
                                        )
                                )
                        )
                );
            }
        }

        return response;
    }

    @Override
    public CalculationType getCalculationType() {
        return CalculationType.Crowflight;
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(Math.toRadians(lat2 - lat1) / 2), 2) +
                Math.pow(Math.sin(Math.toRadians(lon2 - lon1) / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }
}
