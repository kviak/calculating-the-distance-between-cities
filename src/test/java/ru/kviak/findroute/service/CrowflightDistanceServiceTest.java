package ru.kviak.findroute.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.kviak.findroute.mapper.CityMapper;
import ru.kviak.findroute.model.CityDistanceResponse;
import ru.kviak.findroute.persistence.entity.CityEntity;
import ru.kviak.findroute.persistence.repository.CityRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

public class CrowflightDistanceServiceTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CrowflightDistanceService distanceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void calculate_ShouldReturnCorrectDistanceBetweenCities() {
        // Arrange
        CityEntity city1 = new CityEntity();
        city1.setId(1L);
        city1.setLatitude(52.520008);
        city1.setLongitude(13.404954);

        CityEntity city2 = new CityEntity();
        city2.setId(2L);
        city2.setLatitude(48.8566);
        city2.setLongitude(2.3522);

        List<Long> fromCityIds = new ArrayList<>();
        fromCityIds.add(city1.getId());

        List<Long> toCityIds = new ArrayList<>();
        toCityIds.add(city2.getId());

        when(cityRepository.findAllByIdIn(anyList()))
                .thenReturn(List.of(city1, city2));

        CityDistanceResponse expectedResponse = new CityDistanceResponse(
                cityMapper.mapTo(city1),
                cityMapper.mapTo(city2),
                BigDecimal.valueOf(878.91)  // Expected distance in km
        );

        // Act
        List<CityDistanceResponse> response = distanceService.calculate(fromCityIds, toCityIds);

        // Assert
        assertEquals(4, response.size());
        assertEquals(expectedResponse.getToCity(), response.get(0).getToCity());
        assertEquals(expectedResponse.getFromCity(), response.get(0).getFromCity());
    }
}
