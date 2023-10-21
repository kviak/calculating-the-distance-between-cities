package ru.kviak.findroute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kviak.findroute.model.CalculationType;
import ru.kviak.findroute.model.CityDistanceResponse;
import ru.kviak.findroute.model.CityResponse;
import ru.kviak.findroute.exception.CityNotFoundException;
import ru.kviak.findroute.exception.RouteToCityNotFoundException;
import ru.kviak.findroute.mapper.CityMapper;
import ru.kviak.findroute.persistence.entity.CityEntity;
import ru.kviak.findroute.persistence.entity.DistanceEntity;
import ru.kviak.findroute.persistence.repository.CityRepository;
import ru.kviak.findroute.persistence.repository.DistanceRepository;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MatrixDistanceService implements DistanceService {

    private final DistanceRepository distanceRepository;
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
                                        findPathDijkstraAlgorithm(
                                                cityMapper.mapTo(fromCityEntity),
                                                cityMapper.mapTo(toCityEntity)
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
        return CalculationType.DISTANCE_MATRIX;
    }

    public Double findPathDijkstraAlgorithm(CityResponse sourceDto, CityResponse targetDto) {

        CityEntity source = cityRepository.findById(sourceDto.getId()).orElse(null);
        CityEntity target = cityRepository.findById(targetDto.getId()).orElse(null);

        if (source == null || target == null) {
            System.out.println("Source or target city not found.");
            throw new CityNotFoundException();
        }

        Map<CityEntity, Double> distanceMap = new HashMap<>();
        Map<CityEntity, CityEntity> previousCityMap = new HashMap<>();
        Set<CityEntity> visitedCities = new HashSet<>();

        distanceMap.put(source, 0.0);

        while (!visitedCities.contains(target)) {
            CityEntity currentCityEntity = getCityWithShortestDistance(distanceMap, visitedCities).orElseThrow(RouteToCityNotFoundException::new);
            visitedCities.add(currentCityEntity);

            List<DistanceEntity> outgoingDistanceEntities = distanceRepository.findDistanceByFromCity_Name(currentCityEntity.getName()).orElseThrow(RouteToCityNotFoundException::new);

            for (DistanceEntity distanceEntity : outgoingDistanceEntities) {
                CityEntity neighbor = distanceEntity.getToCity();
                double tentativeDistance = distanceMap.get(currentCityEntity) + distanceEntity.getDistance();

                if (tentativeDistance < distanceMap.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distanceMap.put(neighbor, tentativeDistance);
                    previousCityMap.put(neighbor, currentCityEntity);
                }
            }
        }

        if (!distanceMap.containsKey(target)) {
            System.out.println("No path found from " + source.getName() + " to " + target.getName());
            throw new RouteToCityNotFoundException();
        } else {
            List<CityEntity> shortestPath = new ArrayList<>();
            CityEntity current = target;

            while (current != null) {
                shortestPath.add(current);
                current = previousCityMap.get(current);
            }
            Collections.reverse(shortestPath);
            return distanceMap.get(target);
        }
    }

    private Optional<CityEntity> getCityWithShortestDistance(Map<CityEntity, Double> distanceMap, Set<CityEntity> visitedCities) {
        CityEntity shortestDistanceCityEntity = null;
        double shortestDistance = Double.MAX_VALUE;

        for (CityEntity cityEntity : distanceMap.keySet()) {
            if (!visitedCities.contains(cityEntity) && distanceMap.get(cityEntity) < shortestDistance) {
                shortestDistance = distanceMap.get(cityEntity);
                shortestDistanceCityEntity = cityEntity;
            }
        }
        return Optional.ofNullable(shortestDistanceCityEntity) ;
    }

}
