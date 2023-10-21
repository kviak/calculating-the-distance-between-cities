package ru.kviak.findroute.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.kviak.findroute.mapper.CityMapper;
import ru.kviak.findroute.model.CityResponse;
import ru.kviak.findroute.persistence.entity.CityEntity;
import ru.kviak.findroute.persistence.repository.CityRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CityService cityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCities() {
        CityEntity city1 = new CityEntity();
        city1.setId(1L);
        city1.setName("City 1");

        CityEntity city2 = new CityEntity();
        city2.setId(2L);
        city2.setName("City 2");

        List<CityEntity> cities = Arrays.asList(city1, city2);

        CityResponse cityResponse1 = new CityResponse();
        cityResponse1.setId(1L);
        cityResponse1.setName("City 1");

        CityResponse cityResponse2 = new CityResponse();
        cityResponse2.setId(2L);
        cityResponse2.setName("City 2");

        List<CityResponse> expectedCityResponses = Arrays.asList(cityResponse1, cityResponse2);

        when(cityRepository.findAll()).thenReturn(cities);
        when(cityMapper.mapTo(city1)).thenReturn(cityResponse1);
        when(cityMapper.mapTo(city2)).thenReturn(cityResponse2);

        // Act
        List<CityResponse> actualCityResponses = cityService.getAllCites();

        // Assert
        assertEquals(expectedCityResponses, actualCityResponses);
        verify(cityRepository, times(1)).findAll();
        verifyNoMoreInteractions(cityRepository);
        verify(cityMapper, times(1)).mapTo(city1);
        verify(cityMapper, times(1)).mapTo(city2);
        verifyNoMoreInteractions(cityMapper);
    }
}
