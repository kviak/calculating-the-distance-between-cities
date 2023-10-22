package ru.kviak.findroute.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.kviak.findroute.mapper.CityMapper;
import ru.kviak.findroute.model.CalculationType;
import ru.kviak.findroute.persistence.repository.CityRepository;
import ru.kviak.findroute.persistence.repository.DistanceRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DistanceServiceProviderTest {

    private DistanceServiceProvider distanceServiceProvider;

    private DistanceService crowFlightService;
    private DistanceService matrixService;
    private DistanceService allService;

    @Mock
    private CityRepository cityRepository;
    @Mock
    private DistanceRepository distanceRepository;
    @Mock
    private CityMapper cityMapper;

    @BeforeEach
    void setUp() {
        crowFlightService = new CrowflightDistanceService(cityRepository, cityMapper);
        matrixService = new MatrixDistanceService(distanceRepository, cityRepository, cityMapper);
        allService = new AllDistanceService((CrowflightDistanceService) crowFlightService, (MatrixDistanceService)matrixService);

        List<DistanceService> distanceServices = Arrays.asList(crowFlightService, matrixService, allService);
        distanceServiceProvider = new DistanceServiceProvider(distanceServices);
    }

    @Test
    void getDistanceService_withExistingCalculationType_shouldReturnCrowflightService() {
        DistanceService result = distanceServiceProvider.getDistanceService(CalculationType.CROWFLIGHT);
        assertEquals(crowFlightService.getClass(), result.getClass());
    }

    @Test
    void getDistanceService_withExistingCalculationType_shouldReturnMatrixService() {
        DistanceService result = distanceServiceProvider.getDistanceService(CalculationType.DISTANCE_MATRIX);
        assertEquals(matrixService.getClass(), result.getClass());
    }

    @Test
    void getDistanceService_withExistingCalculationType_shouldReturnAllService() {
        DistanceService result = distanceServiceProvider.getDistanceService(CalculationType.ALL);
        assertEquals(allService.getClass(), result.getClass());
    }

    @Test
    void getDistanceService_withNonExistingCalculationType_shouldReturnNull() {
        DistanceService result = distanceServiceProvider.getDistanceService(null);
        assertNull(result);
    }
}

