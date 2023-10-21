package ru.kviak.findroute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kviak.findroute.model.CityResponse;
import ru.kviak.findroute.mapper.CityMapper;
import ru.kviak.findroute.persistence.repository.CityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    @Transactional(readOnly = true)
    public List<CityResponse> getAllCites(){
        return cityRepository.findAll().stream()
                .map(cityMapper::mapTo)
                .toList();
    }
}
