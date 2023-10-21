package ru.kviak.findroute.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kviak.findroute.model.xml.CitiesAndDistancesWrapper;
import ru.kviak.findroute.model.xml.CityXmlUpload;
import ru.kviak.findroute.model.xml.Distance;
import ru.kviak.findroute.exception.InvalidXmlDataException;
import ru.kviak.findroute.persistence.entity.CityEntity;
import ru.kviak.findroute.persistence.entity.DistanceEntity;
import ru.kviak.findroute.persistence.repository.CityRepository;
import ru.kviak.findroute.persistence.repository.DistanceRepository;

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
    public void parseCitiesAndDistances(InputStream xmlInput) throws InvalidXmlDataException {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            CitiesAndDistancesWrapper data = xmlMapper.readValue(xmlInput, CitiesAndDistancesWrapper.class);

            List<CityEntity> cities = data.getCities().stream()
                    .map(this::mapToCity)
                    .collect(Collectors.toList());
            cityRepository.saveAll(cities);

            List<DistanceEntity> distanceEntities = data.getDistances().stream()
                    .map(this::mapToDistance)
                    .collect(Collectors.toList());
            distanceRepository.saveAll(distanceEntities);
        } catch (IOException e) {
            throw new InvalidXmlDataException("Invalid input data", e);
        }
    }

    private CityEntity mapToCity(CityXmlUpload cityDto) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName(cityDto.getName());
        cityEntity.setLongitude(cityDto.getLongitude());
        cityEntity.setLatitude(cityDto.getLatitude());
        return cityEntity;
    }

    private DistanceEntity mapToDistance(Distance distanceDto) {
        DistanceEntity distanceEntity = new DistanceEntity();
        distanceEntity.setFromCity(cityRepository.findByName(distanceDto.getFromCity()).get());
        distanceEntity.setToCity(cityRepository.findByName(distanceDto.getToCity()).get());
        distanceEntity.setDistance(distanceDto.getDistance());
        return distanceEntity;
    }
}
