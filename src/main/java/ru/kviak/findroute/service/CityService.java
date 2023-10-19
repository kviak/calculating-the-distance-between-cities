package ru.kviak.findroute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kviak.findroute.dto.CityDto;
import ru.kviak.findroute.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    @Transactional(readOnly = true)
    public List<CityDto> getAllCites(){
        List<CityDto> list = new ArrayList<>();
        cityRepository.findAll().forEach(city -> {list.add(new CityDto(city.getId(),
                            city.getName(),
                            city.getLongitude(),
                            city.getLatitude()));});
        return list;
    }
}
