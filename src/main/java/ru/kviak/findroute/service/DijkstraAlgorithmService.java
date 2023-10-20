package ru.kviak.findroute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kviak.findroute.dto.CityDto;
import ru.kviak.findroute.dto.DistanceDto;
import ru.kviak.findroute.exception.CityNotFoundException;
import ru.kviak.findroute.exception.RouteToCityNotFoundException;
import ru.kviak.findroute.model.City;
import ru.kviak.findroute.model.Distance;
import ru.kviak.findroute.repository.CityRepository;
import ru.kviak.findroute.repository.DistanceRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DijkstraAlgorithmService {
    private final CityRepository cityRepository;
    private final DistanceRepository distanceRepository;


    @Transactional(readOnly = true)
    public DistanceDto findPathDijkstraAlgorithm(CityDto sourceDto, CityDto targetDto) {
//        System.out.println("From: " + list.get(0).get(0).getName() + " To: " + list.get(1).get(0).getName());

        City source = cityRepository.findById(sourceDto.getId()).orElse(null);
        City target = cityRepository.findById(targetDto.getId()).orElse(null);

        if (source == null || target == null) {
            System.out.println("Source or target city not found.");
            throw new CityNotFoundException();
        }

        Map<City, Double> distanceMap = new HashMap<>();
        Map<City, City> previousCityMap = new HashMap<>();
        Set<City> visitedCities = new HashSet<>();

        distanceMap.put(source, 0.0);

        while (!visitedCities.contains(target)) {
            City currentCity = getCityWithShortestDistance(distanceMap, visitedCities).orElseThrow(RouteToCityNotFoundException::new);
            visitedCities.add(currentCity);

            List<Distance> outgoingDistances = distanceRepository.findDistanceByFromCity_Name(currentCity.getName()).orElseThrow(RouteToCityNotFoundException::new);

            for (Distance distance : outgoingDistances) {
                City neighbor = distance.getToCity();
                double tentativeDistance = distanceMap.get(currentCity) + distance.getDistance();

                if (tentativeDistance < distanceMap.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distanceMap.put(neighbor, tentativeDistance);
                    previousCityMap.put(neighbor, currentCity);
                }
            }
        }

        if (!distanceMap.containsKey(target)) {
            System.out.println("No path found from " + source.getName() + " to " + target.getName());
            throw new RouteToCityNotFoundException();
        } else {
            List<City> shortestPath = new ArrayList<>();
            City current = target;

            while (current != null) {
                shortestPath.add(current);
                current = previousCityMap.get(current);
            }

            Collections.reverse(shortestPath);

//            System.out.println("Shortest path from " + source.getName() + " to " + target.getName() + ":");
//            for (City city : shortestPath) {
//                System.out.println(city.getName());
//            }
//            System.out.println("Total distance: " + distanceMap.get(target));
            return new DistanceDto(sourceDto.getName(), targetDto.getName(), distanceMap.get(target));
        }
    }

    private Optional<City> getCityWithShortestDistance(Map<City, Double> distanceMap, Set<City> visitedCities) {
        City shortestDistanceCity = null;// тут был = null
        double shortestDistance = Double.MAX_VALUE;

        for (City city : distanceMap.keySet()) {
            if (!visitedCities.contains(city) && distanceMap.get(city) < shortestDistance) {
                shortestDistance = distanceMap.get(city);
                shortestDistanceCity = city;
            }
        }
        return Optional.ofNullable(shortestDistanceCity) ;
    }
}
