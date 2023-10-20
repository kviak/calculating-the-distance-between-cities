package ru.kviak.findroute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kviak.findroute.dto.CityDto;
import ru.kviak.findroute.dto.DistanceDto;
import ru.kviak.findroute.repository.DistanceRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculateDistanceService {
    private final DistanceRepository distanceRepository;
    private final DijkstraAlgorithmService finderAlgorithmService;

    public List<DistanceDto> getAll(){
        List<DistanceDto> list = new ArrayList<>();
        distanceRepository.findAll().forEach(distance -> {
            list.add(new DistanceDto(distance.getFromCity().getName(),
                    distance.getFromCity().getName(),
                    distance.getDistance()));});
        return list;
    }

    public List<?> calculate(String calculateType, List<List<CityDto>> list){
        switch (calculateType){
            case "Crowflight":
                return calculateCrowflight(list.get(0).get(0),  list.get(1).get(0));
            case "Distance Matrix":
                return Collections.singletonList(finderAlgorithmService.findPathDijkstraAlgorithm(list));
            case "All":
                System.out.println("All"); break;
            default:
                System.out.println("Incorrect Calculation Type!"); // TODO: Сделать исключения и их хендлинг
        }
        return null;
    }

    public List<DistanceDto> calculateCrowflight(CityDto fromCity, CityDto toCity){
        return Collections.singletonList(new DistanceDto(fromCity.getName(),
                toCity.getName(),
                haversine(fromCity.getLatitude(), fromCity.getLongitude(), toCity.getLatitude(), toCity.getLongitude())));
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        // Радиус Земли в километрах (или в других единицах, если необходимо)
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }
}
