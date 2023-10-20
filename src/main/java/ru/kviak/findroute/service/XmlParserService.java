package ru.kviak.findroute.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kviak.findroute.dto.CitiesAndDistancesDto;
import ru.kviak.findroute.dto.CityDtoView;
import ru.kviak.findroute.dto.DistanceDto;
import ru.kviak.findroute.model.City;
import ru.kviak.findroute.model.Distance;
import ru.kviak.findroute.repository.CityRepository;
import ru.kviak.findroute.repository.DistanceRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class XmlParserService {
    private final CityRepository cityRepository;
    private final DistanceRepository distanceRepository;

    @Transactional
    public void parseCitiesAndDistances(InputStream xmlInput) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        CitiesAndDistancesDto data = xmlMapper.readValue(xmlInput, CitiesAndDistancesDto.class);

        List<City> cities = data.getCities().stream()
                .map(this::mapToCity)
                .collect(Collectors.toList());
        cityRepository.saveAll(cities);

        List<Distance> distances = data.getDistances().stream()
                .map(this::mapToDistance)
                .collect(Collectors.toList());
        distanceRepository.saveAll(distances);
    }

    private City mapToCity(CityDtoView cityDto) {
        City city = new City();
        city.setName(cityDto.getName());
        city.setLongitude(cityDto.getLongitude());
        city.setLatitude(cityDto.getLatitude());
        return city;
    }

    private Distance mapToDistance(DistanceDto distanceDto) {
        Distance distance = new Distance();
        distance.setFromCity(cityRepository.findByName(distanceDto.getFromCity()).get());
        distance.setToCity(cityRepository.findByName(distanceDto.getToCity()).get());
        distance.setDistance(distanceDto.getDistance());
        return distance;
    }
}
